package service;

import exception.ChartServiceException;
import exception.QuantizationException;
import javafx.scene.chart.XYChart;
import model.behaviour.SignalReconstructionType;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 27/10/2017.
 */
public interface ChartService
{
	XYChart.Series<Double, Double> renderRealSignalChart(Signal signal) throws ChartServiceException;
	XYChart.Series<Double, Integer> renderRealSignalHistogram(Signal signal, int intervalCount) throws ChartServiceException;

	/**
	 * Reconstruction
	 */
	XYChart.Series<Double, Double> reconstructQuantizationSignal(Signal signal, SignalReconstructionType reconstructionType)
			throws ChartServiceException, QuantizationException;
}
