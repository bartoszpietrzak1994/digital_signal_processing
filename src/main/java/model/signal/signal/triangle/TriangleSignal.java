package model.signal.signal.triangle;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import exception.SignalParametersException;
import model.behaviour.ParameterType;
import model.signal.SignalType;
import model.signal.base.type.PeriodicSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component(value = "TRIANGLE_SIGNAL")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
		Complex periodCounter = new Complex(getPeriodBySample(sample), 0.0D);

		// two complex numbers declared below are possible return values
		Complex ascendingReturnValue = amplitude.divide((dutyCycle.multiply(period)))
				.multiply(sample.subtract(periodCounter.multiply(period)).subtract(initialTime));
		Complex descendingReturnValue = (((Complex.ONE.negate()
				.multiply(amplitude)).divide((period.divide(period.multiply(dutyCycle))))).multiply(sample.subtract(periodCounter.multiply(period))
				.subtract(initialTime))).add(amplitude.multiply(Complex.ONE.subtract(dutyCycle)));

		// left end of interval
		Complex ascendingReturnValueLeftIntervalEnd = periodCounter.multiply(period).subtract(initialTime);
		// right end of interval
		Complex ascendingReturnValueRightIntervalEnd = dutyCycle.multiply(period).add(periodCounter.multiply(period)).add(initialTime);

		if (sample.getReal() >= ascendingReturnValueLeftIntervalEnd.getReal() && sample.getReal() < ascendingReturnValueRightIntervalEnd.getReal())
		{
			return ascendingReturnValue;
		}

		// left end of interval
		Complex descendingReturnValueLeftIntervalEnd = dutyCycle.multiply(period).add(initialTime).add(periodCounter.multiply(period));
		// right end of interval
		Complex descendingReturnValueRightIntervalEnd = period.add(periodCounter.multiply(period)).add(initialTime);

		if (sample.getReal() >= descendingReturnValueLeftIntervalEnd.getReal() && sample.getReal() < descendingReturnValueRightIntervalEnd.getReal())
		{
			return descendingReturnValue;
		}

		return Complex.ZERO;
	}

	@Override
	public boolean areSampleCalculationParametersProvided()
	{
		return ObjectUtils.allNotNull(amplitude, period, initialTime, duration, dutyCycle);
	}
}
