package service;

import exception.*;
import model.behaviour.QuantizationType;
import model.filter.FilterType;
import model.signal.base.Signal;
import service.request.ResolveSignalRequest;
import service.request.SignalsOperationRequest;
import service.response.ResolveSignalResponse;
import service.response.SignalQuantizationResponse;
import service.response.SignalsCalculationResponse;

import java.util.List;

/**
 * Created by bartoszpietrzak on 25/10/2017.
 */
public interface SignalService
{
    /**
     * In-memory Signal storage
     */
    Signal findSignal(String signalId) throws SignalRepositoryException;

    /**
     * IO Signal operations
     */
    boolean saveListOfSignalsInFile(List<Signal> signals, String path) throws SignalIOException;

    List<Signal> loadSignalsFromFile(String filePath) throws SignalIOException;

    /**
     * Signal on charts presentation
     */
    ResolveSignalResponse processResolveSignalRequest(ResolveSignalRequest request) throws SignalParametersException;

    /**
     * Signal operations
     */
    SignalsCalculationResponse processSignalOperationRequest(SignalsOperationRequest request) throws
            DigitalSignalProcessingException;

    /**
     * Quantization
     */
    SignalQuantizationResponse performSignalQuantization(String signalId, QuantizationType quantizationType, double
            quantLevel) throws QuantizationException, SignalRepositoryException;

    /**
     * Filters
     */
    SignalsCalculationResponse performFilterOnSignal(String signalId, FilterType filterType, int m) throws
            SignalRepositoryException, SignalParametersException;
}
