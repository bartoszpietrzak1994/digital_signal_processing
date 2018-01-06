package service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import exception.*;
import model.filter.Filter;
import model.filter.FilterType;
import model.filter.LowPassFilter;
import model.window.WindowFunction;
import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.behaviour.QuantizationType;
import model.quantization.SignalQuantization;
import model.signal.base.Signal;
import repository.SignalRepository;
import service.request.ResolveSignalRequest;
import service.request.ResolveSignalRequestDataExtractor;
import service.request.SignalsOperationRequest;
import service.response.ResolveSignalResponse;
import service.response.SignalQuantizationResponse;
import service.response.SignalsCalculationResponse;
import utils.calculator.SignalPropertiesCalculator;
import utils.calculator.SignalReconstructionParametersCalculator;
import utils.calculator.SignalValuesCalculator;
import utils.file.SignalAdapter;
import utils.filter.FilterResolver;
import utils.operation.SignalOperationResolver;
import utils.operation.SignalsOperationsCalculator;
import utils.quantization.SignalQuantizationResolver;
import utils.signal.SignalTypeResolver;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
@Component
public class SignalServiceImpl implements SignalService
{
	@Autowired
	private ResolveSignalRequestDataExtractor dataExtractor;

	@Autowired
	private SignalPropertiesCalculator signalPropertiesCalculator;

	@Autowired
	private SignalTypeResolver signalTypeResolver;

	@Autowired
	private SignalOperationResolver signalOperationResolver;

	@Autowired
	private SignalValuesCalculator signalValuesCalculator;

	@Autowired
	private SignalRepository signalRepository;

	@Autowired
	private SignalAdapter signalAdapter;

	@Autowired
	private SignalQuantizationResolver signalQuantizationResolver;

	@Autowired
	private SignalReconstructionParametersCalculator reconstructionParametersCalculator;

	@Autowired
    private ConvolutionService convolutionService;

	@Autowired
    private FilterResolver filterResolver;

	@Override
	public Signal findSignal(String signalId) throws SignalRepositoryException
	{
		return signalRepository.findOne(signalId);
	}

	@Override
	public boolean saveListOfSignalsInFile(List<Signal> signals, String path) throws SignalIOException
	{
		Gson gson = new Gson();

		String signalsAsJson = gson.toJson(signals);

		try(Writer writer =  new FileWriter(path))
		{
			writer.write(signalsAsJson);
		}
		catch (IOException e)
		{
			throw SignalIOException.unableToExportDataToFile(signals, path);
		}

		return true;
	}

	@Override
	public List<Signal> loadSignalsFromFile(String filePath) throws SignalIOException
	{
		Gson gson = new GsonBuilder().registerTypeAdapter(new TypeToken<List<Signal>>(){}.getType(), signalAdapter).create();

		List<Signal> signals = new ArrayList<>();
		try(Reader reader = new FileReader(filePath))
		{
			signals = gson.fromJson(reader, new TypeToken<List<Signal>>(){}.getType());
		}
		catch (Exception e)
		{
			throw SignalIOException.unableToImportDataFromFile(filePath);
		}

		if (signals.isEmpty())
		{
			throw SignalIOException.noSignalsFoundInGivenFile(filePath);
		}

		return signals;
	}

	@Override
	public ResolveSignalResponse processResolveSignalRequest(ResolveSignalRequest request) throws SignalParametersException
	{
		Signal signal = signalTypeResolver.resolveSignalByType(request.getSignalType());
		dataExtractor.extractDataFromSignalChartRequest(request, signal);

		signal.setSamples(signalValuesCalculator.getSampleList(signal.getSamplingRate(), signal.getInitialTime(), signal.getEndTime()));
		signal.setValues(signalValuesCalculator.calculateSignalValues(signal));

		signal.setAverageValue(signalPropertiesCalculator.calculateAverageValue(signal));
		signal.setAbsoluteAverageValue(signalPropertiesCalculator.calculateAbsoluteAverageValue(signal));
		signal.setSignalPower(signalPropertiesCalculator.calculateSignalPower(signal));
		signal.setSignalVariance(signalPropertiesCalculator.calculateVariance(signal));
		signal.setSignalRootMeanSquareValue(signalPropertiesCalculator.calculateRootMeanSquareValue(signal));

		String signalId = signalRepository.add(signal);

		ResolveSignalResponse resolveSignalResponse = ResolveSignalResponse.builder()
				.averageSignalValue(signal.getAverageValue())
				.absoluteAverageSignalValue(signal.getAbsoluteAverageValue())
				.signalPower(signal.getSignalPower())
				.signalVariance(signal.getSignalVariance())
				.signalRootMeanSquareValue(signal.getSignalRootMeanSquareValue())
				.build();

		resolveSignalResponse.setSignalParametersResponse(resolveSignalResponse.new SignalParametersResponse(signalId,
				signal.getSamplingRate(),
				signal.getInitialTime(),
				signal.getDuration(),
				signal.getSignalType().name()));

		return resolveSignalResponse;
	}

	@Override
	public SignalsCalculationResponse processSignalOperationRequest(SignalsOperationRequest request) throws DigitalSignalProcessingException
	{
		Signal first = signalRepository.findOne(request.getIdFirst());
		Signal second = signalRepository.findOne(request.getIdSecond());

		SignalsOperationsCalculator signalsOperationsCalculator = signalOperationResolver.resolveOperationByType(request.getOperation());

		if (!signalsOperationsCalculator.shouldCalculationBePerformed(first, second))
		{
			throw SignalParametersException.unableToPerformSignalsOperation();
		}

		Signal result = signalTypeResolver.resolveSignalByType(signalsOperationsCalculator.resolveSignalType(first.getSignalType(), second.getSignalType())
				.name());

		// TODO
		result = signalsOperationsCalculator.calculate(first, second, result);

		String signalId = signalRepository.add(result);

		// TODO
		SignalsCalculationResponse signalsCalculationResponse = new SignalsCalculationResponse();
		signalsCalculationResponse.setSignalParametersResponse(signalsCalculationResponse.new SignalParametersResponse(
				signalId,
				result.getSamplingRate(),
				result.getInitialTime(),
				result.getDuration(),
				result.getSignalType().name()));

		return signalsCalculationResponse;
	}

	@Override
	public SignalQuantizationResponse performSignalQuantization(String signalId, QuantizationType quantizationType, double quantLevel)
			throws QuantizationException, SignalRepositoryException
	{
		Signal signal = signalRepository.findOne(signalId);

		SignalQuantization quantization = signalQuantizationResolver.resolve(quantizationType);
		Complex complexQuantLevel = new Complex(quantLevel, 0.0D);
		signal = quantization.signalQuantization(signal, complexQuantLevel);

		signalRepository.update(signal);

		return SignalQuantizationResponse.builder()
				.SNR(reconstructionParametersCalculator.calculateSNR(signal))
				.MD(reconstructionParametersCalculator.calculateMD(signal))
				.MSE(reconstructionParametersCalculator.calculateMSE(signal))
				.PSNR(reconstructionParametersCalculator.calculatePSNR(signal))
				.build();
	}

	@Override
	public void performWindowFunctionOnSignal(String signalId, WindowFunction windowFunction) throws SignalRepositoryException
	{
		Signal signal = signalRepository.findOne(signalId);

		List<Complex> values = signal.getValues();
		int m = values.size();

		Complex value;
		Complex windowedValue;
		for (int i = 0; i < values.size(); i++)
		{
			value = values.get(i);
			windowedValue = new Complex(windowFunction.calculate(value.getReal(), m), 0.0D);
			values.set(i, windowedValue);
		}

		signalRepository.update(signal);
	}

    @Override
    public void performFilterOnSignal(String signalId, FilterType filterType) throws SignalRepositoryException, SignalParametersException, FilterException
    {
        Signal signal = signalRepository.findOne(signalId);
        List<Complex> values = signal.getValues();

        Filter filter = filterResolver.resolve(filterType);

        List<Complex> filterValues = new ArrayList<>();
        double k = signal.getSamplingRate().getReal() / (1.0 / signal.getPeriod().getReal());

        for (int i = 0; i < values.size(); i++)
        {
             filterValues.add(filter.calculate(values.get(i).getReal(), k));
        }

        convolutionService.calculateConvolution(signal.getValues(), filterValues);
    }
}
