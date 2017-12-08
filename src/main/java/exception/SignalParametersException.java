package exception;

import java.util.Set;

import org.apache.commons.math.complex.Complex;

import model.behaviour.ParameterType;

/**
 * Created by bartoszpietrzak on 16/10/2017.
 */
public class SignalParametersException extends DigitalSignalProcessingException
{
	private SignalParametersException()
	{
		super();
	}

	private SignalParametersException(DigitalSignalProcessingErrorCode errorCode, String message)
	{
		super(errorCode, message);
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

	public static SignalParametersException operationTypeNotSupported(String operationType)
	{
		return new SignalParametersException(DigitalSignalProcessingErrorCode.OPERATION_TYPE_NOT_SUPPORTED,
				String.format("Operation of type %s is not supported", operationType));
	}

	public static SignalParametersException unableToCalculatePeriodNumber(Complex sample)
	{
		return new SignalParametersException(
				DigitalSignalProcessingErrorCode.UNABLE_TO_CALCULATE_PERIOD_NUMBER,
				String.format("Unable to calculate signalFrequency number for sample of value %s", sample.getReal()));
	}

	public static SignalParametersException unableToCalculateSignalValues()
	{
		return new SignalParametersException(
				DigitalSignalProcessingErrorCode.SIGNAL_SAMPLES_NOT_AVAILABLE,
				"Unable to calculate signal values - samples are not available.");
	}

	public static SignalParametersException unableToPerformSignalsOperation()
	{
		return new SignalParametersException(DigitalSignalProcessingErrorCode.TWO_SIGNALS_OPERATION_NOT_POSSIBLE, "Unable to perform two-signals calculation");
	}
}
