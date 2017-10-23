package model.signal.impulse;

import java.util.List;
import java.util.Map;

import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import model.behaviour.ParameterType;
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
	public Complex calculate(Map<ParameterType, Complex> values)
	{
		return null;
	}
}
