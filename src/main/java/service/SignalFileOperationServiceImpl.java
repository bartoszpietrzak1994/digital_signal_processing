package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exception.SignalIOException;
import model.signal.base.Signal;
import org.springframework.beans.factory.annotation.Autowired;
import service.client.SignalFileOperationService;
import utils.file.SignalAdapter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SignalFileOperationServiceImpl implements SignalFileOperationService
{
    @Autowired
    private SignalAdapter signalAdapter;

    @Override
    public boolean saveListOfSignalsInFile(List<Signal> signals, String path) throws SignalIOException
    {
        Gson gson = new Gson();

        String signalsAsJson = gson.toJson(signals);

        try (Writer writer = new FileWriter(path))
        {
            writer.write(signalsAsJson);
        } catch (IOException e)
        {
            throw SignalIOException.unableToExportDataToFile(signals, path);
        }

        return true;
    }

    @Override
    public List<Signal> loadSignalsFromFile(String filePath) throws SignalIOException
    {
        Gson gson = new GsonBuilder().registerTypeAdapter(new TypeToken<List<Signal>>()
        {
        }.getType(), signalAdapter).create();

        List<Signal> signals = new ArrayList<>();
        try (Reader reader = new FileReader(filePath))
        {
            signals = gson.fromJson(reader, new TypeToken<List<Signal>>()
            {
            }.getType());
        } catch (Exception e)
        {
            throw SignalIOException.unableToImportDataFromFile(filePath);
        }

        if (signals.isEmpty())
        {
            throw SignalIOException.noSignalsFoundInGivenFile(filePath);
        }

        return signals;
    }
}
