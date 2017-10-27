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

		Complex amplitudeIntervalLeftLimit = new Complex(periodCounter.getReal() * this.period.getReal() + this.initialTime.getReal(), 0.0D);
		Complex amplitudeIntervalRightLimit = new Complex(this.dutyCycle.getReal() * this.period.getReal() + amplitudeIntervalLeftLimit.getReal(), 0.0D);

		if (sample.getReal() >= amplitudeIntervalLeftLimit.getReal() && sample.getReal() < amplitudeIntervalRightLimit.getReal())
		{
			return this.amplitude;
		}

		Complex minusAmplitudeIntervalLeftLimit = new Complex(
				(this.dutyCycle.getReal() * this.period.getReal()) + this.initialTime.getReal() + (periodCounter.getReal() * this.period.getReal()), 0.0D);
		Complex minusAmplitudeIntervalRightLimit = new Complex((this.period.getReal() + (periodCounter.getReal() * this.period.getReal())
				+ this.initialTime.getReal()), 0.0D);

		if (sample.getReal() >= minusAmplitudeIntervalLeftLimit.getReal() && sample.getReal() < minusAmplitudeIntervalRightLimit.getReal())
		{
			return this.amplitude.negate();
		}

		return null;
	}
}
