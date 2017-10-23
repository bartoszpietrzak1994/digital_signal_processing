package exception;

import java.util.Set;

import model.behaviour.ParameterType;

/**
 * Created by bartoszpietrzak on 16/10/2017.
 */
public class SignalParametersException extends Exception
{
	public SignalParametersException()
	{
		super();
	}

	public SignalParametersException(String message)
	{
		super(message);
	}

	public static SignalParametersException calculationDataNotProvided(Set<ParameterType> actualParameters, Set<ParameterType> applicableParameters)
	{
		return new SignalParametersException(String.format(
				"Calculation not possible for parameters: %s. Expected parameters: %s",
				actualParameters,
				applicableParameters));
	}
}
