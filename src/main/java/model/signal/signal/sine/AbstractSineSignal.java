package model.signal.signal.sine;

import java.util.List;

import org.apache.commons.math.complex.Complex;

import com.google.common.collect.Sets;

import model.behaviour.ParameterType;
import model.signal.AbstractSignal;

/**
 * Created by bartoszpietrzak on 16/10/2017.
 */
public abstract class AbstractSineSignal extends AbstractSignal
{
	public AbstractSineSignal(
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
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE, ParameterType.PERIOD, ParameterType.INITIAL_TIME, ParameterType.DURATION);
	}
}
