package model.signal.signal.rectangular;

import java.util.List;

import org.apache.commons.math.complex.Complex;

import model.signal.AbstractSignal;

/**
 * Created by bartoszpietrzak on 16/10/2017.
 */
public abstract class AbstractRectangularSignal extends AbstractSignal
{
	public AbstractRectangularSignal(
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
	}
}
