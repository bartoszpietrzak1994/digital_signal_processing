package exception;

import lombok.Getter;

/**
 * Created by bartoszpietrzak on 19/10/2017.
 */
@Getter
public class CalculationDataNotProvidedException extends RuntimeException
{
	private String message;

	public CalculationDataNotProvidedException(String message)
	{

	}
}
