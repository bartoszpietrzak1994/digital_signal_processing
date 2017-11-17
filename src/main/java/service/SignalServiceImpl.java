package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import exception.DigitalSignalProcessingException;
import exception.SignalIOException;
import exception.SignalParametersException;
import exception.SignalRepositoryException;
import model.signal.base.Signal;
import repository.SignalRepository;
import service.request.ResolveSignalRequest;
import service.request.ResolveSignalRequestDataExtractor;
import service.request.SignalsOperationRequest;
import service.response.ResolveSignalResponse;
import service.response.SignalsCalculationResponse;
import utils.calculator.SignalPropertiesCalculator;
import utils.calculator.SignalValuesCalculator;
import utils.file.SignalAdapter;
import utils.operation.SignalOperationResolver;
import utils.operation.SignalsOperationsCalculator;
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

	@Override
	public Signal findSignal(String signalId) throws SignalRepositoryException
	{
		return signalRepository.findOne(signalId);
	}

	@Override
	public boolean saveListOfSignalsInFile(List<Signal> signals, String path) throws SignalIOException
	{
		Gson gson = new GsonBuilder().registerTypeAdapter(new TypeToken<List<Signal>>(){}.getType(), signalAdapter).create();
		try
		{
			gson.toJson(signals, new FileWriter(path));
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

		List<Signal> signals;
		try
		{
			signals = gson.fromJson(new FileReader(filePath), new TypeToken<List<Signal>>(){}.getType());
		}
		catch (FileNotFoundException e)
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
				signal.getDuration()));

		return resolveSignalResponse;
	}

	@Override
	public ResolveSignalResponse provideImaginaryChartData(ResolveSignalRequest request)
	{
		return null;
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
				result.getDuration()));

		return signalsCalculationResponse;
	}

}
