package service.client;

import exception.QuantizationException;
import exception.SignalRepositoryException;
import model.behaviour.QuantizationType;
import service.response.SignalQuantizationResponse;

public interface QuantizationService
{
    SignalQuantizationResponse performSignalQuantization(String signalId, QuantizationType quantizationType, double
            quantLevel) throws QuantizationException, SignalRepositoryException;
}
