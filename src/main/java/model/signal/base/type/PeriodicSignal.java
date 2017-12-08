package model.signal.base.type;

import org.apache.commons.math.complex.Complex;

import exception.SignalParametersException;
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

	/**
	 * Finds out in which signalFrequency given sample is.
	 * Applicable only for periodic signals.
	 * @param sample
	 * @return
	 * @throws SignalParametersException
	 */
	protected int getPeriodBySample(Complex sample) throws SignalParametersException
	{
		int periodCounter = 0;

		while ((periodCounter - 1) * this.period.getReal() < this.endTime.getReal())
		{
			if (sample.getReal() >= (periodCounter - 1) * this.period.getReal() && sample.getReal() <= periodCounter  * this.period.getReal())
			{
				return periodCounter;
			}

			periodCounter++;
		}

		throw SignalParametersException.unableToCalculatePeriodNumber(sample);
	}
}