package service.client;

import exception.DigitalSignalProcessingException;
import exception.SignalParametersException;
import exception.SignalRepositoryException;
import model.signal.base.Signal;
import service.request.ResolveSignalRequest;
import service.request.SignalsOperationRequest;
import service.response.ResolveSignalResponse;
import service.response.SignalsCalculationResponse;

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
     * Signal on charts presentation
     */
    ResolveSignalResponse processResolveSignalRequest(ResolveSignalRequest request) throws SignalParametersException;

    /**
     * Signal operations
     */
    SignalsCalculationResponse processSignalOperationRequest(SignalsOperationRequest request) throws
            DigitalSignalProcessingException;
}
