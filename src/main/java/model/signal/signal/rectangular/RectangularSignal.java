package model.signal.signal.rectangular;

import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import model.signal.SignalType;

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
		this.signalType = SignalType.RECTANGULAR_SIGNAL;
	}

	@Override
	public Complex calculate(Complex sample)
	{
		return null;
	}
}
