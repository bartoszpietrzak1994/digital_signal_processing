package exception;

public class FilterException extends DigitalSignalProcessingException
{
    public FilterException()
    {
        super();
    }

    private FilterException(DigitalSignalProcessingErrorCode errorCode, String message)
    {
        super(errorCode, message);
    }

    public static FilterException filterTypeNotSupported(String filterType)
    {
        return new FilterException(DigitalSignalProcessingErrorCode.FILTER_TYPE_NOT_FOUND, "Received filter type: " + filterType);
    }
}
