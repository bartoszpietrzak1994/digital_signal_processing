package exception;

import lombok.Getter;

/**
 * Created by bartoszpietrzak on 28/10/2017.
 */
public class DigitalSignalProcessingException extends Exception
{
	@Getter
	private DigitalSignalProcessingErrorCode errorCode;

	public DigitalSignalProcessingException(DigitalSignalProcessingErrorCode errorCode, String message)
	{
		super(message);
		this.errorCode = errorCode;
	}

	public DigitalSignalProcessingException()
	{
		super();
	}
}
