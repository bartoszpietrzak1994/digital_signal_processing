package model.request;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Data;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
@Component
@Data
@Builder
public class SignalPropertiesCalculationRequest
{
	public Complex amplitude;
	public Complex initialTime;
	public Complex duration;
	public Complex period;
	public Complex dutyCycle;
	public Complex samplingRate;
	public String signalType;
}
