package model.reconstruction;

import org.springframework.stereotype.Component;

import javafx.scene.chart.XYChart;
import model.behaviour.SignalReconstructionType;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 07/12/2017.
 */
@Component
public class ZOHSignalReconstruction extends SignalReconstruction
{
	public ZOHSignalReconstruction()
	{
		super();
		this.reconstructionType = SignalReconstructionType.ZOH;
	}

	@Override
	public XYChart.Series<Double, Double> signalReconstruction(Signal signal)
	{
		return null;
	}
}
