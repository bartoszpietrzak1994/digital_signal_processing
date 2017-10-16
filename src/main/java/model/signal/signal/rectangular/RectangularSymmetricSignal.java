package model.signal.signal.rectangular;

import java.util.List;
import java.util.Map;

import org.apache.commons.math.complex.Complex;

import com.google.common.collect.Sets;

import model.behaviour.ParameterType;
import model.signal.AbstractSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
public class RectangularSymmetricSignal extends AbstractSignal
{
	public RectangularSymmetricSignal(
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
		this.applicableParameters = Sets.newHashSet(
				ParameterType.AMPLITUDE,
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
