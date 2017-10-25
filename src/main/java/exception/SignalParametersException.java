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

	public static SignalParametersException calculationDataNotProvided(Set<ParameterType> expected)
	{
		return new SignalParametersException(String.format(
				"Calculation not possible. Expected parameters were not provided. Expected parameters: %s",
				expected));
	}

	public static SignalParametersException signalTypeNotSupported(String signalType)
	{
		return new SignalParametersException(String.format("Signal of type %s is not supported.", signalType));
	}
}
