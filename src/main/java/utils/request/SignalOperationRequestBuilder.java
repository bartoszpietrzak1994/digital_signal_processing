package utils.request;

import org.apache.commons.lang3.StringUtils;

import lombok.Builder;
import lombok.Data;
import service.request.SignalsOperationRequest;

/**
 * Created by bartoszpietrzak on 14/11/2017.
 */
@Builder
@Data
public class SignalOperationRequestBuilder
{
	private String firstSignalData;
	private String secondSignalData;
	private String signalOperation;

	public SignalsOperationRequest toRequest()
	{
		String firstSignalId = null;
		String secondSignalId = null;

		if (StringUtils.isNotEmpty(this.firstSignalData))
		{
			String[] data = firstSignalData.split("\\;");
			firstSignalId = data[0];
		}

		if (StringUtils.isNotEmpty(this.secondSignalData))
		{
			String[] data = secondSignalData.split("\\;");
			secondSignalId = data[0];
		}

		return SignalsOperationRequest.builder().idFirst(firstSignalId).idSecond(secondSignalId).operation(signalOperation).build();
	}
}
