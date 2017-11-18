package service;

import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exception.ChartServiceException;
import javafx.scene.chart.XYChart;
import manager.ChartManager;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
@Component
public class ChartServiceImpl implements ChartService
{
	@Override
	public XYChart.Series<Double, Double> renderRealSignalChart(Signal signal) throws ChartServiceException
	{
		if (!validateSignal(signal))
		{
			throw ChartServiceException.samplesAndValuesDoNotMatch(signal.getSamples().size(), signal.getValues().size());
		}

		XYChart.Series<Double, Double> signalChart = new XYChart.Series<>();

		List<Complex> samples = signal.getSamples();
		List<Complex> values = signal.getValues();

		Complex currentSample;
		Complex currentValue;

		for (int i = 0; i < signal.getSamples().size(); i++)
		{
			currentSample = samples.get(i);
			currentValue = values.get(i);
			signalChart.getData().add(new XYChart.Data(currentSample.getReal(), currentValue.getReal()));
		}

		return signalChart;
	}

	@Override
	public XYChart.Series<Double, Double> renderRealSignalHistogram(Signal signal) throws ChartServiceException
	{
		return null;
	}

	private boolean validateSignal(Signal signal)
	{
		return signal.getSamples().size() == signal.getValues().size();
	}
}
