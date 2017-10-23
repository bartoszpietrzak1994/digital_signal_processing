package model.signal.impulse;

import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import model.signal.SignalType;
import model.signal.base.type.NonPeriodicSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
@NoArgsConstructor
public class StepFunction extends NonPeriodicSignal
{
	public StepFunction(
			Complex amplitude,
			Complex initialTime,
			Complex duration,
			Complex dutyCycle,
			Complex samplingRate,
			List<Complex> values)
	{
		super(amplitude, initialTime, duration, dutyCycle, samplingRate, values);

		this.signalType = SignalType.STEP_FUNCTION;
	}

	@Override
	public Complex calculate(Complex sample)
	{
		return null;
	}
}
