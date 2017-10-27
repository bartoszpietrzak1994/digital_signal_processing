package service;

import exception.ChartServiceException;
import javafx.scene.chart.XYChart;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 27/10/2017.
 */
public interface ChartService
{
	XYChart.Series<String, Double> renderRealSignalChart(Signal signal) throws ChartServiceException;
	XYChart.Series<Double, Double> renderImaginarySignalChart(Signal signal) throws ChartServiceException;
}
