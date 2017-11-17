package utils.request;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math.complex.Complex;

import lombok.Builder;
import lombok.Data;
import service.request.ResolveSignalRequest;

/**
 * Created by bartoszpietrzak on 27/10/2017.
 */
@Data
@Builder
public class ResolveSignalRequestBuilder
{
	public String amplitude;
	public String initialTime;
	public String duration;
	public String period;
	public String dutyCycle;
	public String samplingRate;
	public String valuePresenceProbability;
	public String amplitudeRiseSample;
	public String signalType;

	public ResolveSignalRequest toRequest()
	{
		return ResolveSignalRequest.builder()
				// default values for initial time and sampling rate are 0 and 1 - the rest will be set to null if the SignalOperation is empty
				.initialTime(StringUtils.isNotEmpty(this.initialTime) ? new Complex(Double.valueOf(this.initialTime), 0.0D) : Complex.ZERO)
				.samplingRate(StringUtils.isNotEmpty(this.samplingRate) ? new Complex(Double.valueOf(this.samplingRate), 0.0D) : Complex.ONE)
				.valuePresenceProbability(StringUtils.isNotEmpty(this.valuePresenceProbability) ?
						new Complex(Double.valueOf(this.valuePresenceProbability), 0.0D) :
						Complex.ONE)
				.amplitudeRiseSample(StringUtils.isNotEmpty(this.amplitudeRiseSample) ?
						new Complex(Double.valueOf(this.amplitudeRiseSample), 0.0D) :
						Complex.ONE)
				.amplitude(StringUtils.isNotEmpty(this.amplitude) ? new Complex(Double.valueOf(this.amplitude), 0.0D) : null)
				.duration(StringUtils.isNotEmpty(this.duration) ? new Complex(Double.valueOf(this.duration), 0.0D) : null)
				.dutyCycle(StringUtils.isNotEmpty(this.dutyCycle) ?  new Complex(Double.valueOf(this.dutyCycle), 0.0D) : null)
				.period(StringUtils.isNotEmpty(this.period) ? new Complex(Double.valueOf(this.period), 0.0D) : null)
				.signalType(this.signalType)
				.build();
	}
}
