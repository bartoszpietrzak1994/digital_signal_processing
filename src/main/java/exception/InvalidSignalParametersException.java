package exception;

import lombok.Getter;

/**
 * Created by bartoszpietrzak on 16/10/2017.
 */
@Getter
public class InvalidSignalParametersException extends RuntimeException
{
	private String message;

	public InvalidSignalParametersException(String message)
	{

	}
}
