package model.signal.base.type;

import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 19/10/2017.
 */
public abstract class PeriodicSignal extends Signal
{
	public PeriodicSignal()
	{
		super();
		this.isPeriodic = Boolean.TRUE;
	}
}