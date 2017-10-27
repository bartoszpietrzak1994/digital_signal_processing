package model.signal.signal.rectangular;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import model.signal.SignalType;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
public class RectangularSignal extends AbstractRectangularSignal
{
	public RectangularSignal()
	{
		super();
		this.signalType = SignalType.RECTANGULAR_SIGNAL;
	}

	@Override
	public Complex calculate(Complex sample) throws SignalParametersException
	{
		Complex periodCounter = getPeriodBySample(sample);

		Complex amplitudeIntervalLeftLimit = new Complex(periodCounter.getReal() * this.period.getReal() + this.initialTime.getReal(), 0.0D);
		Complex amplitudeIntervalRightLimit = new Complex(this.dutyCycle.getReal() * this.period.getReal() + amplitudeIntervalLeftLimit.getReal(), 0.0D);

		if (sample.getReal() >= amplitudeIntervalLeftLimit.getReal() && sample.getReal() < amplitudeIntervalRightLimit.getReal())
		{
			return this.amplitude;
		}

		Complex zeroIntervalLeftLimit = new Complex(
				(this.dutyCycle.getReal() * this.period.getReal()) - periodCounter.getReal() * this.period.getReal() + this.initialTime.getReal(),
				0.0D);
		Complex zeroIntervalRightLimit = new Complex((this.period.getReal() + (periodCounter.getReal() * this.period.getReal()) + this.initialTime.getReal()),
				0.0D);

		if (sample.getReal() >= zeroIntervalLeftLimit.getReal() && sample.getReal() < zeroIntervalRightLimit.getReal())
		{
			return Complex.ZERO;
		}

		return null;
	}
}
