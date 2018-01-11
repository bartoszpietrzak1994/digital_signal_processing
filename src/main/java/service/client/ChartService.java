package service.client;

import exception.ChartServiceException;
import exception.QuantizationException;
import exception.SignalRepositoryException;
import javafx.scene.chart.XYChart;
import model.behaviour.SignalReconstructionType;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 27/10/2017.
 */
public interface ChartService
{
    XYChart.Series<Double, Double> renderRealSignalChart(String signalId) throws ChartServiceException,
			SignalRepositoryException;

    XYChart.Series<Double, Integer> renderRealSignalHistogram(String signalId, int intervalCount) throws
			ChartServiceException, SignalRepositoryException;

    /**
     * Reconstruction
     */
    XYChart.Series<Double, Double> reconstructQuantizationSignal(String signalId, SignalReconstructionType
			reconstructionType) throws ChartServiceException, QuantizationException, SignalRepositoryException;
}
