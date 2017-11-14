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
	private int idFirst;
	private int idSecond;
	private String operation;
}
