package model.reconstruction;

import javafx.scene.chart.XYChart;
import lombok.Getter;
import model.behaviour.SignalReconstructionType;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 07/12/2017.
 */
public abstract class SignalReconstruction
{
	@Getter
	protected SignalReconstructionType reconstructionType;

	public abstract XYChart.Series<Double, Double> signalReconstruction(Signal signal);
}
