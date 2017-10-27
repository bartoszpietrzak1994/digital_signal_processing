package manager;

import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import javafx.scene.chart.XYChart;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 27/10/2017.
 */
@Component
public class ChartManager
{
	public boolean validateSignal(Signal signal)
	{
		return signal.getSamples().size() == signal.getValues().size();
	}
	
	public XYChart.Series<String, Double> getChartOutOfRealSignal(Signal signal)
	{
		XYChart.Series<String, Double> signalChart = new XYChart.Series<>();

		List<Complex> samples = signal.getSamples();
		List<Complex> values = signal.getValues();

		Complex currentSample = null;
		Complex currentValue = null;

		for (int i = 0; i < signal.getSamples().size(); i++)
		{
			currentSample = samples.get(i);
			currentValue = values.get(i);
			signalChart.getData().add(new XYChart.Data(currentSample.getReal(), currentValue.getReal()));
		}

		return signalChart;
	}

	public XYChart.Series<Double, Double> getChartOutOfImaginarySignal(Signal signal)
	{
		XYChart.Series<Double, Double> signalChart = new XYChart.Series<>();

		List<Complex> samples = signal.getSamples();
		List<Complex> values = signal.getValues();

		Complex currentSample = null;
		Complex currentValue = null;

		for (int i = 0; i < signal.getSamples().size(); i++)
		{
			currentSample = samples.get(i);
			currentValue = values.get(i);
			signalChart.getData().add(new XYChart.Data<>(currentSample.getImaginary(), currentValue.getImaginary()));
		}

		return signalChart;
	}
}
