package exception;

import java.util.Set;

import org.apache.commons.math.complex.Complex;

import lombok.Getter;
import model.behaviour.ParameterType;

/**
 * Created by bartoszpietrzak on 16/10/2017.
 */
public class SignalParametersException extends Exception
{
	@Getter
	private DigitalSignalProcessingErrorCode errorCode;

	private SignalParametersException(DigitalSignalProcessingErrorCode errorCode, String message)
	{
		super(message);
		this.errorCode = errorCode;
	}

	public static SignalParametersException calculationDataNotProvided(Set<ParameterType> expectedParameters)
	{
		return new SignalParametersException(DigitalSignalProcessingErrorCode.CALCULATION_DATA_NOT_PROVIDED,
				String.format("Calculation not possible. Expected parameters were not provided. Expected parameters: %s", expectedParameters));
	}

	public static SignalParametersException signalTypeNotSupported(String signalType)
	{
		return new SignalParametersException(DigitalSignalProcessingErrorCode.SIGNAL_TYPE_NOT_SUPPORTED,
				String.format("Signal of type %s is not supported.", signalType));
	}

	public static SignalParametersException unableToCalculatePeriodNumber(Complex sample)
	{
		return new SignalParametersException(
				DigitalSignalProcessingErrorCode.UNABLE_TO_CALCULATE_PERIOD_NUMBER,
				String.format("Unable to calculate period number for sample of value %s", sample.getReal()));
	}
}
