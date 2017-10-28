package exception;

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

	public static SignalIOException signalValuesNotAvailable()
	{
		return new SignalIOException(DigitalSignalProcessingErrorCode.SIGNAL_VALUES_NOT_AVAILABLE, "Signal values were not provided.");
	}
}
