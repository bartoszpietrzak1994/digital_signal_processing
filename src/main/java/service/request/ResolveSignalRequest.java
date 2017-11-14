package service.request;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResolveSignalRequest
{
	public Complex amplitude;
	public Complex initialTime;
	public Complex duration;
	public Complex period;
	public Complex dutyCycle;
	public Complex samplingRate;
	public String signalType;
}
