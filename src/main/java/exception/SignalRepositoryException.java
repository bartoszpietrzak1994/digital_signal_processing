package exception;

/**
 * Created by bartoszpietrzak on 25/10/2017.
 */
public class SignalRepositoryException extends DigitalSignalProcessingException
{
	private SignalRepositoryException()
	{
		super();
	}

	private SignalRepositoryException(DigitalSignalProcessingErrorCode errorCode, String message)
	{
		super(errorCode, message);
	}

	public static SignalRepositoryException signalNotPresentInRepository(String id)
	{
		return new SignalRepositoryException(
				DigitalSignalProcessingErrorCode.SIGNAL_NOT_PRESENT_IN_REPOSITORY,
				String.format("Signal with id: %s is not present in signal repository.", id));
	}
}
