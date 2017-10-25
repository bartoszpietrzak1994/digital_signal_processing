package service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import exception.SignalIOException;
import exception.SignalParametersException;
import javafx.scene.chart.XYChart;
import manager.SignalManager;
import model.request.SignalChartRequest;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
@Component
public class SignalService
{
	@Autowired
	private SignalManager signalManager;

	public boolean saveSignal(Signal signal) throws SignalIOException
	{
		if (CollectionUtils.isEmpty(signal.getValues()))
		{
			throw SignalIOException.signalValuesNotAvailable();
		}

		return false;
	}

	public Signal loadSignal(int signalId)
	{

	}

	public XYChart<Double, Double> provideChartData(SignalChartRequest request) throws SignalParametersException
	{
		Signal signal = signalManager.resolveSignalByType(request.getSignalType());

		signalManager.extractDataFromSignalChartRequest(request, signal);
		if (!signal.areParametersProvided())
		{
			throw SignalParametersException.calculationDataNotProvided(signal.getApplicableParameters());
		}

		signal.setSamples(signalManager.getSignalSamples(signal));

		List<Complex> values = new ArrayList<>();
		List<Complex> samples = signal.getSamples();

		for (int i = 0; i < samples.size(); i++)
		{
			values.add(signal.calculate(samples.get(i)));
		}

		signal.setValues(values);

		return null;
	}

	public XYChart<Double, Double> provideImaginaryChartData(SignalChartRequest request)
	{
		return null;
	}

}
