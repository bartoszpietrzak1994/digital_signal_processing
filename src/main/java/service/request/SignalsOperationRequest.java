package service.request;

import lombok.Builder;
import lombok.Data;

/**
 * Created by bartoszpietrzak on 13/11/2017.
 */
@Data
@Builder
public class SignalsOperationRequest
{
	private String idFirst;
	private String idSecond;
	private String operation;
}
