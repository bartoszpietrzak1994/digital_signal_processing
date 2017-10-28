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
		Complex ascendingReturnValue = amplitude.divide((dutyCycle.multiply(period)))
				.multiply(sample.subtract(periodCounter.multiply(period)).subtract(initialTime));
		Complex descendingReturnValue = (((Complex.ONE.negate()
				.multiply(amplitude)).divide((period.divide(period.multiply(dutyCycle))))).multiply(sample.subtract(periodCounter.multiply(period))
				.subtract(initialTime))).add(amplitude.multiply(Complex.ONE.subtract(dutyCycle)));

		Complex ascendingReturnValueLeftInterval = periodCounter.multiply(period).subtract(initialTime);
		Complex ascendingReturnValueRightInterval = dutyCycle.multiply(period).add(periodCounter.multiply(period)).add(initialTime);

		if (sample.getReal() >= ascendingReturnValueLeftInterval.getReal() && sample.getReal() < ascendingReturnValueRightInterval.getReal())
		{
			return ascendingReturnValue;
		}

		Complex descendingReturnValueLeftInterval = dutyCycle.multiply(period).add(initialTime).add(periodCounter.multiply(period));
		Complex descendingReturnValueRightInterval = period.add(periodCounter.multiply(period)).add(initialTime);

		if (sample.getReal() >= descendingReturnValueLeftInterval.getReal() && sample.getReal() < descendingReturnValueRightInterval.getReal())
		{
			return descendingReturnValue;
		}

		return null;
	}

	@Override
	public boolean areParametersProvided()
	{
		return ObjectUtils.allNotNull(amplitude, period, initialTime, duration, dutyCycle);
	}
}
