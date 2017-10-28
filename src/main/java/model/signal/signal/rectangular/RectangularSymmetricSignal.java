package model.signal.signal.rectangular;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import model.signal.SignalType;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
public class RectangularSymmetricSignal extends AbstractRectangularSignal
{
	public RectangularSymmetricSignal()
	{
		super();
		this.signalType = SignalType.RECTANGULAR_SYMMETRIC_SIGNAL;
	}

	@Override
	public Complex calculate(Complex sample) throws SignalParametersException
	{
		Complex periodCounter = getPeriodBySample(sample);

		// left end of interval
		Complex amplitudeIntervalLeftEnd = new Complex(periodCounter.getReal() * this.period.getReal() + this.initialTime.getReal(), 0.0D);
		// right end of interval
		Complex amplitudeIntervalRightEnd = new Complex(this.dutyCycle.getReal() * this.period.getReal() + amplitudeIntervalLeftEnd.getReal(), 0.0D);

		if (sample.getReal() >= amplitudeIntervalLeftEnd.getReal() && sample.getReal() < amplitudeIntervalRightEnd.getReal())
		{
			return this.amplitude;
		}

		// left end of interval
		Complex minusAmplitudeIntervalLeftEnd = new Complex(
				(this.dutyCycle.getReal() * this.period.getReal()) + this.initialTime.getReal() + (periodCounter.getReal() * this.period.getReal()), 0.0D);
		// right end of interval
		Complex minusAmplitudeIntervalRightEnd = new Complex((this.period.getReal() + (periodCounter.getReal() * this.period.getReal())
				+ this.initialTime.getReal()), 0.0D);

		if (sample.getReal() >= minusAmplitudeIntervalLeftEnd.getReal() && sample.getReal() < minusAmplitudeIntervalRightEnd.getReal())
		{
			return this.amplitude.negate();
		}

		return null;
	}
}
