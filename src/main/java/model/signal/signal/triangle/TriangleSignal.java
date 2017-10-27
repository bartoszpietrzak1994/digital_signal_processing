package model.signal.signal.triangle;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import exception.SignalParametersException;
import model.behaviour.ParameterType;
import model.signal.SignalType;
import model.signal.base.type.PeriodicSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
public class TriangleSignal extends PeriodicSignal
{
	public TriangleSignal()
	{
		super();
		this.signalType = SignalType.TRIANGLE_SIGNAL;
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE, ParameterType.PERIOD, ParameterType.DURATION, ParameterType.DUTY_CYCLE);
	}

	@Override
	public Complex calculate(Complex sample) throws SignalParametersException
	{
		Complex periodCounter = getPeriodBySample(sample);
		Complex ascendingReturnValue = this.amplitude.divide((this.dutyCycle.multiply(this.period)))
				.multiply(sample.subtract(periodCounter.multiply(this.period)).subtract(this.initialTime));
		Complex descendingReturnValue = (((Complex.ONE.negate()
				.multiply(this.amplitude)).divide((this.period.divide(this.period.multiply(this.dutyCycle))))).multiply(sample.subtract(periodCounter.multiply(
				this.period)).subtract(this.initialTime))).add(this.amplitude.multiply(Complex.ONE.subtract(this.dutyCycle)));

		Complex ascendingReturnValueLeftInterval = periodCounter.multiply(this.period).subtract(this.initialTime);
		Complex ascendingReturnValueRightInterval = this.dutyCycle.multiply(this.period).add(periodCounter.multiply(this.period)).add(this.initialTime);

		if (sample.getReal() >= ascendingReturnValueLeftInterval.getReal() && sample.getReal() < ascendingReturnValueRightInterval.getReal())
		{
			return ascendingReturnValue;
		}

		Complex descendingReturnValueLeftInterval = this.dutyCycle.multiply(this.period).add(this.initialTime).add(periodCounter.multiply(this.period));
		Complex descendingReturnValueRightInterval = this.period.add(periodCounter.multiply(this.period)).add(this.initialTime);

		if (sample.getReal() >= descendingReturnValueLeftInterval.getReal() && sample.getReal() < descendingReturnValueRightInterval.getReal())
		{
			return descendingReturnValue;
		}

		return null;
	}

	@Override
	public boolean areParametersProvided()
	{
		return ObjectUtils.allNotNull(this.amplitude, this.period, this.initialTime, this.duration, this.dutyCycle);
	}
}
