package service.client;

import exception.SignalParametersException;
import exception.SignalRepositoryException;
import model.filter.FilterType;
import service.response.ResolveSignalResponse;

public interface FilterService
{
    ResolveSignalResponse performFilterOnSignal(String signalId, FilterType filterType, int m, boolean
            additionalWindowFunction) throws SignalRepositoryException, SignalParametersException;
}
