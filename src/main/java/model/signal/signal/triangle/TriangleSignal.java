package model.signal.signal.triangle;

import java.util.List;
import java.util.Map;

import org.apache.commons.math.complex.Complex;

import com.google.common.collect.Sets;

import model.behaviour.ParameterType;
import model.signal.base.type.PeriodicSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
public class TriangleSignal extends PeriodicSignal
{
	public TriangleSignal(
			double amplitude, double initialTime, double duration, double period, double dutyCycle, Integer samplingRate, List<Complex> values)
	{
		super(amplitude, initialTime, duration, period, dutyCycle, samplingRate, values);
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE,
				ParameterType.PERIOD,
				ParameterType.INITIAL_TIME,
				ParameterType.DURATION,
				ParameterType.DUTY_CYCLE);
	}

	@Override
	public Complex calculate(Map<ParameterType, Complex> values)
	{
		return null;
	}
}
