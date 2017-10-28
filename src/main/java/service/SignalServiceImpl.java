package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import exception.SignalIOException;
import exception.SignalParametersException;
import exception.SignalRepositoryException;
import manager.SignalManager;
import model.request.SignalPropertiesCalculationRequest;
import model.response.SignalPropertiesCalculationResponse;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
@Component
public class SignalServiceImpl implements SignalService
{
	@Autowired
	private SignalManager signalManager;

	@Override
	public boolean storeSignal(Signal signal) throws SignalIOException
	{
		if (CollectionUtils.isEmpty(signal.getValues()))
		{
			throw SignalIOException.signalValuesNotAvailable();
		}

		return false;
	}

	@Override
	public Signal findSignal(int signalId) throws SignalRepositoryException
	{
		return signalManager.findSignalInRepository(signalId);
	}

	@Override
	public boolean saveSignalInFile(Signal signal, String path)
	{
		return false;
	}

	@Override
	public List<Signal> loadSignalsFromFile(String filePath)
	{
		return null;
	}

	@Override
	public SignalPropertiesCalculationResponse calculateSignalProperties(SignalPropertiesCalculationRequest request) throws SignalParametersException
	{
		Signal signal = signalManager.resolveSignalByType(request.getSignalType());
		signalManager.extractDataFromSignalChartRequest(request, signal);

		if (!signal.areSampleCalculationParametersProvided())
		{
			throw SignalParametersException.calculationDataNotProvided(signal.getApplicableParameters());
		}

		signal.setSamples(signalManager.getSignalSamples(signal));
		signal.setValues(signalManager.calculateSignalValues(signal));

		return SignalPropertiesCalculationResponse.builder()
				.signal(signal)
				.averageSignalValue(signalManager.calculateSignalAverageValue(signal))
				.absoluteAverageSignalValue(signalManager.calculateSignalAbsoluteAverageValue(signal))
				.signalPower(signalManager.calculateSignalPower(signal))
				.signalVariance(signalManager.calculateSignalVariance(signal))
				.signalRootMeanSquareValue(signalManager.calculateSignalRootMeanSquareValue(signal)).build();
	}

	@Override
	public SignalPropertiesCalculationResponse provideImaginaryChartData(SignalPropertiesCalculationRequest request)
	{
		return null;
	}

}
