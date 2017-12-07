package model.reconstruction;

import javafx.scene.chart.XYChart;
import model.behaviour.SignalReconstructionType;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 07/12/2017.
 */
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
		return null;
	}
}
