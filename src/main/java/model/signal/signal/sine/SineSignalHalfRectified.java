package model.signal.signal.sine;

import java.util.List;
import java.util.Map;

import org.apache.commons.math.complex.Complex;

import model.behaviour.ParameterType;
import model.signal.AbstractSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
public class SineSignalHalfRectified extends AbstractSignal
{
	public SineSignalHalfRectified(
			double amplitude,
			double initialTime,
			double duration,
			double period,
			Boolean isPeriodic,
			double dutyCycle,
			Integer samplingRate,
			List<Complex> values)
	{
		super(amplitude, initialTime, duration, period, isPeriodic, dutyCycle, samplingRate, values);
	}

	@Override
	public double calculate(Map<ParameterType, Double> values)
	{
		return 0;
	}
}
