package exception;

import lombok.Getter;

/**
 * Created by bartoszpietrzak on 19/10/2017.
 */
@Getter
public class CalculationDataNotProvidedException extends Exception
{
	public CalculationDataNotProvidedException()
	{
		super();
	}

	public CalculationDataNotProvidedException(String message)
	{
		super(message);
	}
}
