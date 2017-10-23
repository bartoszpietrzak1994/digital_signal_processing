package service;

import java.util.ArrayList;
import java.util.List;

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

	public XYChart<Double, Double> provideRealChartData(SignalChartRequest request) throws SignalParametersException
	{
		Signal signal = signalManager.resolveSignalByType(request.getSignalType());
		signal.setSamples(signalManager.getSignalSamples(
				request.getSamplingRate(),
				request.getInitialTime(),
				request.getInitialTime().add(request.getDuration())));

		extractDataFromSignalChartRequest(request, signal);

		List<Complex> values = new ArrayList<>();
		List<Complex> samples = signal.getSamples();

		for (int i = 0; i < signal.getSamples().size(); i++)
		{
			values.add(signal.calculate(null));
		}

		return null;
	}

	public XYChart<Double, Double> provideImaginaryChartData(SignalChartRequest request)
	{
		return null;
	}

	private void extractDataFromSignalChartRequest(SignalChartRequest request, Signal signal)
	{
		signal.setAmplitude(request.getAmplitude());
		signal.setInitialTime(request.getInitialTime());
		signal.setDuration(request.getDuration());
		signal.setPeriod(request.getPeriod());
		signal.setDutyCycle(signal.getDutyCycle());
		signal.setSamplingRate(request.getSamplingRate());
	}

}
