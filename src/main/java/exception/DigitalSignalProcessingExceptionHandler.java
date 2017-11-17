package exception;

import org.springframework.stereotype.Component;

/**
 * Created by bartoszpietrzak on 28/10/2017.
 */
@Component
public class DigitalSignalProcessingExceptionHandler
{
	private static final String CALCULATION_DATA_NOT_PROVIDED_MESSAGE = "Please check if you provided all necessary data";
	private static final String UNABLE_TO_CALCULATE_PERIOD_NUMBER_MESSAGE = "Please check of you provided valid data";
	private static final String SIGNAL_VALUES_NOT_AVAILABLE_MESSAGE = "Please check if signal values were previously calculated";
	private static final String SIGNAL_TYPE_NOT_SUPPORTED_MESSAGE = "Please check if you provided valid signal type";
	private static final String SIGNAL_TYPE_NOT_GIVEN_BY_USER_MESSAGE = "Please provide signal type";
	private static final String SIGNAL_TYPE_NOT_PRESENT_IN_REPOSITORY_MESSAGE = "Requested signal was not present in repository";

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
				return exceptionErrorCodeAsString + CALCULATION_DATA_NOT_PROVIDED_MESSAGE;
			}
			case UNABLE_TO_CALCULATE_PERIOD_NUMBER:
			{
				return exceptionErrorCodeAsString + UNABLE_TO_CALCULATE_PERIOD_NUMBER_MESSAGE;
			}
			case SIGNAL_TYPE_NOT_SUPPORTED:
			{
				return exceptionErrorCodeAsString + SIGNAL_TYPE_NOT_SUPPORTED_MESSAGE;
			}
			case SIGNAL_TYPE_NOT_GIVEN_BY_USER:
			{
				return exceptionErrorCodeAsString + SIGNAL_TYPE_NOT_GIVEN_BY_USER_MESSAGE;
			}
			case SIGNAL_NOT_PRESENT_IN_REPOSITORY:
			{
				return exceptionErrorCodeAsString + SIGNAL_TYPE_NOT_PRESENT_IN_REPOSITORY_MESSAGE;
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
