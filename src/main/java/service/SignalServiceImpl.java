package service;

import exception.DigitalSignalProcessingException;
import exception.SignalParametersException;
import exception.SignalRepositoryException;
import model.signal.base.Signal;
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
    private SignalReconstructionParametersCalculator reconstructionParametersCalculator;

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

        result = signalsOperationsCalculator.calculate(first, second, result);

        String signalId = signalRepository.add(result);

        SignalsCalculationResponse signalsCalculationResponse = new SignalsCalculationResponse();
        signalsCalculationResponse.setSignalParametersResponse(signalsCalculationResponse.new
				SignalParametersResponse(signalId, result.getSamplingRate(), result.getInitialTime(), result
				.getDuration(), result.getSignalType().name(), false));

        return signalsCalculationResponse;
    }
}
