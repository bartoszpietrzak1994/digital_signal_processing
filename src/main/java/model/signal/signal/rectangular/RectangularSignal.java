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
// TODO Fix me!
@Component(value = "RECTANGULAR_SIGNAL")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
		Complex zeroIntervalLeftEnd = new Complex(
				(this.dutyCycle.getReal() * this.period.getReal()) - periodCounter * this.period.getReal() + this.initialTime.getReal(),
				0.0D);
		// right end of interval
		Complex zeroIntervalRightEnd = new Complex((this.period.getReal() + (periodCounter * this.period.getReal()) + this.initialTime.getReal()),
				0.0D);

		if (sample.getReal() >= zeroIntervalLeftEnd.getReal() && sample.getReal() < zeroIntervalRightEnd.getReal())
		{
			return Complex.ZERO;
		}

		return Complex.ZERO;
	}
}
