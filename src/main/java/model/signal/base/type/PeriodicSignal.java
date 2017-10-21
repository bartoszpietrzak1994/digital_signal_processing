package model.signal.base.type;

import java.util.List;

import org.apache.commons.math.complex.Complex;

import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 19/10/2017.
 */
public abstract class PeriodicSignal extends Signal
{
	public PeriodicSignal(
			double amplitude, double initialTime, double duration, double period, double dutyCycle, Integer samplingRate, List<Complex> values)
	{
		super(amplitude, initialTime, duration, dutyCycle, samplingRate, values);
		this.isPeriodic = Boolean.TRUE;
		this.period = period;
	}
}