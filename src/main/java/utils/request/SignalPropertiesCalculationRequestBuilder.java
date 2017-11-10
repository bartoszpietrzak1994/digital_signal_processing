package utils.request;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math.complex.Complex;

import lombok.Builder;
import lombok.Data;
import model.request.ResolveSignalRequest;

/**
 * Created by bartoszpietrzak on 27/10/2017.
 */
@Data
@Builder
public class SignalPropertiesCalculationRequestBuilder
{
	public String amplitude;
	public String initialTime;
	public String duration;
	public String period;
	public String dutyCycle;
	public String samplingRate;
	public String signalType;

	public ResolveSignalRequest toRequest()
	{
		return ResolveSignalRequest.builder()
				.amplitude(new Complex(StringUtils.isNotEmpty(this.amplitude) ? Double.valueOf(this.amplitude) : 0.0D, 0.0D))
				.duration(new Complex(StringUtils.isNotEmpty(this.duration) ? Double.valueOf(this.duration) : 0.0D, 0.0D))
				.dutyCycle(new Complex(StringUtils.isNotEmpty(this.dutyCycle) ? Double.valueOf(this.dutyCycle) : 0.0D, 0.0D))
				.initialTime(new Complex(StringUtils.isNotEmpty(this.initialTime) ? Double.valueOf(this.initialTime) : 0.0D, 0.0D))
				.period(new Complex(StringUtils.isNotEmpty(this.period) ? Double.valueOf(this.period) : 0.0D, 0.0D))
				.samplingRate(new Complex(StringUtils.isNotEmpty(this.samplingRate) ? Double.valueOf(this.samplingRate) : 0.0D, 0.0D))
				.signalType(this.signalType)
				.build();
	}
}
