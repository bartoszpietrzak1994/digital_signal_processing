package service;

import exception.DigitalSignalProcessingException;
import exception.SignalParametersException;
import exception.SignalRepositoryException;
import model.signal.base.Signal;
import model.window.HammingWindowFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.SignalRepository;
import service.client.SignalService;
import service.request.ResolveSignalRequest;
import service.request.ResolveSignalRequestDataExtractor;
import service.request.SignalsOperationRequest;
import service.response.ResolveSignalResponse;
import service.response.SignalsCalculationResponse;
import utils.calculator.SignalPropertiesCalculator;
import utils.calculator.SignalReconstructionParametersCalculator;
import utils.calculator.SignalValuesCalculator;
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
    private SignalQuantizationResolver signalQuantizationResolver;

    @Autowired
    private SignalReconstructionParametersCalculator reconstructionParametersCalculator;

    @Autowired
    private HammingWindowFunction hammingWindowFunction;

    @Override
    public Signal findSignal(String signalId) throws SignalRepositoryException
    {
        return signalRepository.findOne(signalId);
    }

    @Override
    public ResolveSignalResponse processResolveSignalRequest(ResolveSignalRequest request) throws
			SignalParametersException
    {
        Signal signal = signalTypeResolver.resolveSignalByType(request.getSignalType());
        dataExtractor.extractDataFromSignalChartRequest(request, signal);

        signal.setSamples(signalValuesCalculator.getSampleList(signal.getSamplingRate(), signal.getInitialTime(),
				signal.getEndTime()));
        signal.setValues(signalValuesCalculator.calculateSignalValues(signal));

        signal.setAverageValue(signalPropertiesCalculator.calculateAverageValue(signal));
        signal.setAbsoluteAverageValue(signalPropertiesCalculator.calculateAbsoluteAverageValue(signal));
        signal.setSignalPower(signalPropertiesCalculator.calculateSignalPower(signal));
        signal.setSignalVariance(signalPropertiesCalculator.calculateVariance(signal));
        signal.setSignalRootMeanSquareValue(signalPropertiesCalculator.calculateRootMeanSquareValue(signal));

        String signalId = signalRepository.add(signal);

        ResolveSignalResponse resolveSignalResponse = ResolveSignalResponse.builder().averageSignalValue(signal
				.getAverageValue()).absoluteAverageSignalValue(signal.getAbsoluteAverageValue()).signalPower(signal
				.getSignalPower()).signalVariance(signal.getSignalVariance()).signalRootMeanSquareValue(signal
				.getSignalRootMeanSquareValue()).build();

        resolveSignalResponse.setSignalParametersResponse(resolveSignalResponse.new SignalParametersResponse
				(signalId, signal.getSamplingRate(), signal.getInitialTime(), signal.getDuration(), signal
						.getSignalType().name()));

        return resolveSignalResponse;
    }

    @Override
    public SignalsCalculationResponse processSignalOperationRequest(SignalsOperationRequest request) throws
			DigitalSignalProcessingException
    {
        Signal first = signalRepository.findOne(request.getIdFirst());
        Signal second = signalRepository.findOne(request.getIdSecond());

        SignalsOperationsCalculator signalsOperationsCalculator = signalOperationResolver.resolveOperationByType
				(request.getOperation());

        if (!signalsOperationsCalculator.shouldCalculationBePerformed(first, second))
        {
            throw SignalParametersException.unableToPerformSignalsOperation();
        }

        Signal result = signalTypeResolver.resolveSignalByType(signalsOperationsCalculator.resolveSignalType(first
				.getSignalType(), second.getSignalType()).name());

        // TODO
        result = signalsOperationsCalculator.calculate(first, second, result);

        String signalId = signalRepository.add(result);

        // TODO
        SignalsCalculationResponse signalsCalculationResponse = new SignalsCalculationResponse();
        signalsCalculationResponse.setSignalParametersResponse(signalsCalculationResponse.new
				SignalParametersResponse(signalId, result.getSamplingRate(), result.getInitialTime(), result
				.getDuration(), result.getSignalType().name(), false));

        return signalsCalculationResponse;
    }

//    private SignalsCalculationResponse performWindowFunctionOnValues(List<Complex> values, WindowFunction windowFunction)
//			throws SignalRepositoryException, SignalParametersException
//    {
//        Signal orgSignal = signalRepository.findOne(signalId);
//        Signal windowedSignal = signalTypeResolver.resolveSignalByType(orgSignal.getSignalType().name());
//
//        windowedSignal.setSamplingRate(orgSignal.getSamplingRate());
//        windowedSignal.setInitialTime(orgSignal.getInitialTime());
//        windowedSignal.setDuration(orgSignal.getDuration());
//
//        List<Complex> orgValues = orgSignal.getValues();
//        int m = orgValues.size();
//
//        windowedSignal.setSamples(orgSignal.getSamples());
//        List<Complex> windowedValues = new ArrayList<>();
//
//        Complex value;
//        Complex windowedValue;
//        for (int i = 0; i < orgValues.size(); i++)
//        {
//            value = orgValues.get(i);
//            windowedValue = new Complex(windowFunction.calculate(value.getReal(), m), 0.0D);
//            windowedValues.add(windowedValue);
//        }
//
//        windowedSignal.setValues(windowedValues);
//
//        String windowedSignalId = signalRepository.add(orgSignal);
//
//        SignalsCalculationResponse signalsCalculationResponse = new SignalsCalculationResponse();
//        signalsCalculationResponse.setSignalParametersResponse(signalsCalculationResponse.new
//				SignalParametersResponse(windowedSignalId, orgSignal.getSamplingRate(), orgSignal.getInitialTime(),
//				orgSignal.getDuration(), orgSignal.getSignalType().name(), true));
//
//        return signalsCalculationResponse;
//    }
}
