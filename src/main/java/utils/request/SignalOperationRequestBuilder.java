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
		int firstSignalId = -1;
		int secondSignalId = -1;

		if (StringUtils.isNotEmpty(this.firstSignalData))
		{
			String[] data = firstSignalData.split("\\;");
			firstSignalId = Integer.valueOf(data[0]);
		}

		if (StringUtils.isNotEmpty(this.secondSignalData))
		{
			String[] data = secondSignalData.split("\\;");
			secondSignalId = Integer.valueOf(data[0]);
		}

		return SignalsOperationRequest.builder().idFirst(firstSignalId).idSecond(secondSignalId).operation(signalOperation).build();
	}
}
