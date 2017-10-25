package service;

import java.util.List;

import exception.SignalIOException;
import exception.SignalParametersException;
import exception.SignalRepositoryException;
import javafx.scene.chart.XYChart;
import model.request.SignalChartRequest;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 25/10/2017.
 */
public interface SignalService
{
	/**
	 *	In-memory Signal storage
	 */
	boolean storeSignal(Signal signal) throws SignalIOException;
	Signal findSignal(int signalId) throws SignalRepositoryException;

	/**
	 *	IO Signal operations
	 */
	boolean saveSignalInFile(Signal signal, String path);
	List<Signal> loadSignalsFromFile(String filePath);

	/**
	 * Signal on charts presentation
	 */
	XYChart<Double, Double> provideChartData(SignalChartRequest request) throws SignalParametersException;
	XYChart<Double, Double> provideImaginaryChartData(SignalChartRequest request);
}
