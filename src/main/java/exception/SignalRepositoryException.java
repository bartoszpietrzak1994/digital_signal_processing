package exception;

/**
 * Created by bartoszpietrzak on 25/10/2017.
 */
public class SignalRepositoryException extends Exception
{
	public SignalRepositoryException()
	{
		super();
	}

	public SignalRepositoryException(String message)
	{
		super(message);
	}

	public static SignalRepositoryException signalNotPresentInRepository(int id)
	{
		return new SignalRepositoryException(String.format("Signal with id: %d is not present in signal repository.", id));
	}
}
