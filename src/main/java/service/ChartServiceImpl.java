package service;

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
	@Autowired
	private ChartManager chartManager;

	@Override
	public XYChart.Series<Double, Double> renderRealSignalChart(Signal signal) throws ChartServiceException
	{
		if (!chartManager.validateSignal(signal))
		{
			throw ChartServiceException.samplesAndValuesDoNotMatch(signal.getSamples().size(), signal.getValues().size());
		}

		return chartManager.getChartOutOfRealSignal(signal);
	}

	@Override
	public XYChart.Series<Double, Double> renderImaginarySignalChart(Signal signal) throws ChartServiceException
	{
		if (!chartManager.validateSignal(signal))
		{
			throw ChartServiceException.samplesAndValuesDoNotMatch(signal.getSamples().size(), signal.getValues().size());
		}

		return chartManager.getChartOutOfImaginarySignal(signal);
	}
}
