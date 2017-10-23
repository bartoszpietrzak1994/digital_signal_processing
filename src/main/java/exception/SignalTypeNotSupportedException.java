package exception;

/**
 * Created by bartoszpietrzak on 21/10/2017.
 */
public class SignalTypeNotSupportedException extends RuntimeException
{
	public SignalTypeNotSupportedException()
	{
		super();
	}

	public SignalTypeNotSupportedException(String signalType)
	{
		super(String.format("Signal of type: %s is not supported!", signalType));
	}
}
