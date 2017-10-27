package exception;

import lombok.Getter;

/**
 * Created by bartoszpietrzak on 27/10/2017.
 */
public class ChartServiceException extends Exception
{
	@Getter
	private DigitalSignalProcessingErrorCode errorCode;

	private ChartServiceException(DigitalSignalProcessingErrorCode errorCode, String message)
	{
		super(message);
		this.errorCode = errorCode;
	}

	public static ChartServiceException samplesAndValuesDoNotMatch(int samplesCount, int valuesCount)
	{
		return new ChartServiceException(
				DigitalSignalProcessingErrorCode.SAMPLES_AND_VALUES_DO_NOT_MATCH,
				String.format("Samples quantity: %s do not match values quantity: %s", samplesCount, valuesCount));
	}
}
