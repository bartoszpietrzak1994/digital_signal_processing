package model.reconstruction;

import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import javafx.scene.chart.XYChart;
import model.behaviour.SignalReconstructionType;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 07/12/2017.
 */
@Component
public class FOHSignalReconstrution extends SignalReconstruction
{
	public FOHSignalReconstrution()
	{
		super();
		this.reconstructionType = SignalReconstructionType.FOH;
	}

	@Override
	public XYChart.Series<Double, Double> signalReconstruction(Signal signal)
	{
		XYChart.Series<Double, Double> reconstructionChart = new XYChart.Series<>();

		List<Complex> samples = signal.getSamples();
		List<Complex> values = signal.getQuantizationValues();

		Complex currentSample;
		Complex currentValue;

		for (int i = 0; i < signal.getSamples().size(); i++)
		{
			currentSample = samples.get(i);
			currentValue = values.get(i);
			reconstructionChart.getData().add(new XYChart.Data(currentSample.getReal(), currentValue.getReal()));
		}

		return reconstructionChart;
	}
}
