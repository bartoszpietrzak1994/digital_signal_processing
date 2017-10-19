package model.signal.base.periodic;

import java.util.List;

import org.apache.commons.math.complex.Complex;

import model.signal.base.AbstractSignal;

/**
 * Created by bartoszpietrzak on 19/10/2017.
 */
public abstract class NonPeriodicSignal extends AbstractSignal
{
	public NonPeriodicSignal(
			double amplitude, double initialTime, double duration, double dutyCycle, Integer samplingRate, List<Complex> values)
	{
		super(amplitude, initialTime, duration, dutyCycle, samplingRate, values);
		this.isPeriodic = Boolean.FALSE;
		this.period = 0;
	}
}
