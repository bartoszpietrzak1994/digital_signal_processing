package exception;

/**
 * Created by bartoszpietrzak on 07/12/2017.
 */
public class QuantizationException extends DigitalSignalProcessingException
{
	public QuantizationException()
	{
		super();
	}

	private QuantizationException(DigitalSignalProcessingErrorCode errorCode, String message)
	{
		super(errorCode, message);
	}

	public static QuantizationException quantizationTypeNotSupported(String quantizationType)
	{
		return new QuantizationException(DigitalSignalProcessingErrorCode.QUANTIZATION_TYPE_NOT_SUPPORTED, "Received quantization type: " + quantizationType);
	}
}
