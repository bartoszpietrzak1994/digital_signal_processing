package model.signal.base.type;

import org.apache.commons.math.complex.Complex;

import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 19/10/2017.
 */
public abstract class NonPeriodicSignal extends Signal
{
	public NonPeriodicSignal()
	{
		super();
		this.isPeriodic = Boolean.FALSE;
		this.period = new Complex(0.0D, 0.0D);
	}
}
