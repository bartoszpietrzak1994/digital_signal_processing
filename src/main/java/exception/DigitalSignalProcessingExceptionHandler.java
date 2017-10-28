package exception;

import org.springframework.stereotype.Component;

/**
 * Created by bartoszpietrzak on 28/10/2017.
 */
@Component
public class DigitalSignalProcessingExceptionHandler
{
	public String handle(Exception exception)
	{
		if (!(exception instanceof DigitalSignalProcessingException))
		{
			return DigitalSignalProcessingErrorCode.SYSTEM_ERROR.name();
		}

		DigitalSignalProcessingException digitalSignalProcessingException = null;

		try
		{
			digitalSignalProcessingException = DigitalSignalProcessingException.class.cast(exception);
		}
		catch (ClassCastException e)
		{
			return DigitalSignalProcessingErrorCode.SYSTEM_ERROR.name();
		}

		DigitalSignalProcessingErrorCode exceptionErrorCode = digitalSignalProcessingException.getErrorCode();
		String exceptionErrorCodeAsString = exceptionErrorCode.name() + ". ";

		switch (exceptionErrorCode)
		{
			case CALCULATION_DATA_NOT_PROVIDED:
			{
				return exceptionErrorCodeAsString + "Please check if you provided all necessary data";
			}
			case UNABLE_TO_CALCULATE_PERIOD_NUMBER:
			{
				return exceptionErrorCodeAsString + "Please check of you provided valid data";
			}
			case SIGNAL_VALUES_NOT_AVAILABLE:
			{
				return exceptionErrorCodeAsString + "Please check if signal values were previously calculated";
			}
			case SIGNAL_TYPE_NOT_SUPPORTED:
			{
				return exceptionErrorCodeAsString + "Please check if you provided valid signal type";
			}
			case SIGNAL_TYPE_NOT_GIVEN_BY_USER:
			{
				return exceptionErrorCodeAsString + "Please provide signal type";
			}
			case SIGNAL_NOT_PRESENT_IN_REPOSITORY:
			{
				return exceptionErrorCodeAsString + "Requested signal was not present in repository";
			}
			case SAMPLES_AND_VALUES_DO_NOT_MATCH:
			{
				return exceptionErrorCodeAsString;
			}
			default:
			{
				return DigitalSignalProcessingErrorCode.SYSTEM_ERROR.name();
			}
		}
	}
}
