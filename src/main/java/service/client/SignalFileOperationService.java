package service.client;

import exception.SignalIOException;
import model.signal.base.Signal;

import java.util.List;

public interface SignalFileOperationService
{
    boolean saveListOfSignalsInFile(List<Signal> signals, String path) throws SignalIOException;

    List<Signal> loadSignalsFromFile(String filePath) throws SignalIOException;
}
