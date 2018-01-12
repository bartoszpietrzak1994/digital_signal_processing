package model.signal.signal.rectangular;

import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import model.signal.SignalType;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component(value = "RECTANGULAR_SYMMETRIC_SIGNAL")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
		double periodCounter = getPeriodBySample(sample);

		// left end of interval
		double amplitudeIntervalLeftEnd = periodCounter * this.period.getReal() + this.initialTime.getReal();
		// right end of interval
		double amplitudeIntervalRightEnd = this.dutyCycle.getReal() * this.period.getReal() + amplitudeIntervalLeftEnd;

		if (sample.getReal() >= amplitudeIntervalLeftEnd && sample.getReal() < amplitudeIntervalRightEnd)
		{
			return this.amplitude;
		}

		// left end of interval
		double minusAmplitudeIntervalLeftEnd =
				(this.dutyCycle.getReal() * this.period.getReal()) + this.initialTime.getReal() + (periodCounter * this.period.getReal());

		// right end of interval
		double minusAmplitudeIntervalRightEnd = (this.period.getReal() + (periodCounter * this.period.getReal())
				+ this.initialTime.getReal());

		if (sample.getReal() >= minusAmplitudeIntervalLeftEnd && sample.getReal() < minusAmplitudeIntervalRightEnd)
		{
			return this.amplitude.negate();
		}

		return Complex.ZERO;
	}
}
