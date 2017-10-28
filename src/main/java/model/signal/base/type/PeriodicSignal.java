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
	 * Finds out in which period is given sample.
	 * Applicable only for periodic signals.
	 * @param sample
	 * @return
	 * @throws SignalParametersException
	 */
	protected Complex getPeriodBySample(Complex sample) throws SignalParametersException
	{
		Complex periodCounter = Complex.ONE;

		while (periodCounter.getReal() * this.period.getReal() < this.endTime.getReal())
		{
			if (sample.getReal() >= periodCounter.getReal() * this.period.getReal() && sample.getReal() <= periodCounter.getReal() + 1 * this.period.getReal())
			{
				return periodCounter;
			}

			periodCounter.add(Complex.ONE);
		}

		throw SignalParametersException.unableToCalculatePeriodNumber(sample);
	}
}