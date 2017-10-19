package model.signal.signal.rectangular;

import java.util.List;

import org.apache.commons.math.complex.Complex;

import com.google.common.collect.Sets;

import model.behaviour.ParameterType;
import model.signal.base.periodic.PeriodicSignal;

/**
 * Created by bartoszpietrzak on 16/10/2017.
 */
public abstract class AbstractRectangularSignal extends PeriodicSignal
{
	public AbstractRectangularSignal(
			double amplitude,
			double initialTime,
			double duration,
			double period,
			double dutyCycle,
			Integer samplingRate,
			List<Complex> values)
	{
		super(amplitude, initialTime, duration, period, dutyCycle, samplingRate, values);
		this.applicableParameters = Sets.newHashSet(
				ParameterType.AMPLITUDE,
				ParameterType.PERIOD,
				ParameterType.INITIAL_TIME,
				ParameterType.DURATION,
				ParameterType.DUTY_CYCLE);
	}
}