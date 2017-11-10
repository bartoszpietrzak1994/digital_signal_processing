package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import exception.SignalRepositoryException;
import manager.SignalManager;
import model.request.ResolveSignalRequest;
import model.response.ResolveSignalResponse;
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
	public ResolveSignalResponse processResolveSignalRequest(ResolveSignalRequest request) throws SignalParametersException
	{
		Signal signal = signalManager.resolveSignalByType(request.getSignalType());
		signalManager.extractDataFromSignalChartRequest(request, signal);

		if (!signal.areSampleCalculationParametersProvided())
		{
			throw SignalParametersException.calculationDataNotProvided(signal.getApplicableParameters());
		}

		signal.setSamples(signalManager.getSignalSamples(signal));
		signal.setValues(signalManager.calculateSignalValues(signal));

		signal.setAverageValue(signalManager.calculateSignalAverageValue(signal));
		signal.setAbsoluteAverageValue(signalManager.calculateSignalAbsoluteAverageValue(signal));
		signal.setSignalPower(signalManager.calculateSignalPower(signal));
		signal.setSignalVariance(signalManager.calculateSignalVariance(signal));
		signal.setSignalRootMeanSquareValue(signalManager.calculateSignalRootMeanSquareValue(signal));

		int signalId = signalManager.insertSignalIntoRepository(signal);

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

}
