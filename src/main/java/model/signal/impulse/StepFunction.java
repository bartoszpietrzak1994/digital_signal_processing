package model.signal.impulse;

import java.util.List;
import java.util.Map;

import org.apache.commons.math.complex.Complex;

import model.behaviour.ParameterType;
import model.signal.base.type.NonPeriodicSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
public class StepFunction extends NonPeriodicSignal
{
	public StepFunction(
			double amplitude,
			double initialTime,
			double duration,
			double dutyCycle,
			Integer samplingRate,
			List<Complex> values)
	{
		super(amplitude, initialTime, duration, dutyCycle, samplingRate, values);
	}

	public Complex calculate(Map<ParameterType, Complex> values)
	{
		return null;
	}
}
