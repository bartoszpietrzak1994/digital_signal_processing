package service;

import java.util.List;

import exception.SignalParametersException;
import exception.SignalRepositoryException;
import model.request.ResolveSignalRequest;
import model.response.ResolveSignalResponse;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 25/10/2017.
 */
public interface SignalService
{
	/**
	 *	In-memory Signal storage
	 */
	Signal findSignal(int signalId) throws SignalRepositoryException;

	/**
	 *	IO Signal operations
	 */
	boolean saveSignalInFile(Signal signal, String path);
	List<Signal> loadSignalsFromFile(String filePath);

	/**
	 * Signal on charts presentation
	 */
	ResolveSignalResponse processResolveSignalRequest(ResolveSignalRequest request) throws SignalParametersException;
	ResolveSignalResponse provideImaginaryChartData(ResolveSignalRequest request);
}
