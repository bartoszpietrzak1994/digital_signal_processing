package exception;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
public class SignalIOException extends Exception
{
	public SignalIOException()
	{
		super();
	}

	public static SignalIOException signalValuesNotAvailable()
	{
		return new SignalIOException("Signal values were not provided.");
	}

	public SignalIOException(String message)
	{
		super(message);
	}
}
