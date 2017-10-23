package model.signal.signal.rectangular;

import java.util.List;
import java.util.Map;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import model.behaviour.ParameterType;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
@NoArgsConstructor
public class RectangularSignal extends AbstractRectangularSignal
{
	public RectangularSignal(
			Complex amplitude,
			Complex initialTime,
			Complex duration,
			Complex period,
			Complex dutyCycle,
			Complex samplingRate,
			List<Complex> values)
	{
		super(amplitude, initialTime, duration, period, dutyCycle, samplingRate, values);
	}

	@Override
	public Complex calculate(Map<ParameterType, Complex> values)
	{
		return null;
	}
}
