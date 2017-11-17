package exception;

import java.util.List;
import java.util.stream.Collectors;

import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
public class SignalIOException extends DigitalSignalProcessingException
{
	private SignalIOException()
	{
		super();
	}

	private SignalIOException(DigitalSignalProcessingErrorCode errorCode, String message)
	{
		super(errorCode, message);
	}

	public static SignalIOException unableToExportDataToFile(List<Signal> signals, String path)
	{
		List<String> signalIds = signals.stream().map(Signal::getId).collect(Collectors.toList());
		return new SignalIOException(DigitalSignalProcessingErrorCode.UNABLE_TO_EXPORT_DATA_TO_FILE,
				"Unable to export signals with following ids: " + signalIds + " to file with path: " + path);
	}

	public static SignalIOException unableToImportDataFromFile(String path)
	{
		return new SignalIOException(DigitalSignalProcessingErrorCode.UNABLE_TO_IMPORT_DATA_FROM_FILE, "Unable to import signals from file with path: " + path);
	}

	public static SignalIOException noSignalsFoundInGivenFile(String path)
	{
		return new SignalIOException(DigitalSignalProcessingErrorCode.NO_SIGNALS_FOUND_IN_GIVEN_FILE, "No signals were found in file with path: " + path);
	}
}
