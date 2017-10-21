package model.signal.signal.sine;

import java.util.List;

import org.apache.commons.math.complex.Complex;

import com.google.common.collect.Sets;

import model.behaviour.ParameterType;
import model.signal.base.type.PeriodicSignal;

/**
 * Created by bartoszpietrzak on 16/10/2017.
 */
public abstract class AbstractSineSignal extends PeriodicSignal
{
	public AbstractSineSignal(
			Complex amplitude, Complex initialTime, Complex duration, Complex period, Complex dutyCycle, Integer samplingRate, List<Complex> values)
	{
		super(amplitude, initialTime, duration, period, dutyCycle, samplingRate, values);
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE, ParameterType.PERIOD, ParameterType.INITIAL_TIME, ParameterType.DURATION);
	}
}
