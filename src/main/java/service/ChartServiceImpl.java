package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import com.google.common.collect.Iterables;

import exception.ChartServiceException;
import javafx.scene.chart.XYChart;
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
	public XYChart.Series<Double, Integer> renderRealSignalHistogram(Signal signal, int intervalCount) throws ChartServiceException
	{
		if (!validateSignal(signal))
		{
			throw ChartServiceException.samplesAndValuesDoNotMatch(signal.getSamples().size(), signal.getValues().size());
		}

		XYChart.Series<Double, Integer> signalHistogram = new XYChart.Series<>();

		List<Complex> values = signal.getValues();

		List<Complex> copiedAndSortedValues = new ArrayList<>(values);
		Collections.copy(copiedAndSortedValues, values);
		Collections.sort(copiedAndSortedValues, (val1, val2) -> val1.getReal() > val2.getReal() ? 1 : (val1.getReal() < val2.getReal() ? -1 : 0));

		Map<Complex, Integer> histogram = new HashMap<>();

		Complex currentValue;
		for (int i = 0; i < copiedAndSortedValues.size(); i++)
		{
			currentValue = copiedAndSortedValues.get(i);
			if (!histogram.containsKey(currentValue))
			{
				histogram.put(currentValue, 1);
			}
			else
			{
				histogram.put(currentValue, histogram.get(currentValue) + 1);
			}
		}

		for (Complex key : histogram.keySet())
		{
			signalHistogram.getData().add(new XYChart.Data(String.valueOf(key.getReal()), histogram.get(key)));
		}

		return signalHistogram;

		//		int v = (int) (Iterables.getLast(copiedAndSortedValues, null).getReal() - Iterables.getFirst(copiedAndSortedValues, null).getReal() % intervalCount);
		//		double interval =
		//				(Iterables.getLast(copiedAndSortedValues, null).getReal() - Iterables.getFirst(copiedAndSortedValues, null).getReal()) / (double) intervalCount;
		//
		//		List<Double> intervals = new ArrayList<>();
		//		intervals.add(Iterables.getFirst(copiedAndSortedValues, null).getReal());

	}

	private boolean validateSignal(Signal signal)
	{
		return signal.getSamples().size() == signal.getValues().size();
	}
}
