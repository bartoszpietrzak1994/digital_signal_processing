package model.signal.impulse;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import model.behaviour.ParameterType;
import model.signal.SignalType;
import model.signal.base.type.NonPeriodicSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
public class StepFunction extends NonPeriodicSignal
{
	public StepFunction()
	{
		super();
		this.signalType = SignalType.STEP_FUNCTION;
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE,
				ParameterType.INITIAL_TIME,
				ParameterType.DURATION,
				ParameterType.AMPLITUDE_RISE_TIME);
	}

	@Override
	public Complex calculate(Complex sample)
	{
		if (sample.getReal() > this.amplitudeRiseTime.getReal())
		{
			return this.amplitude;
		}
		else if (sample.getReal() == amplitudeRiseTime.getReal())
		{
			return this.amplitude.divide(new Complex(2.0D, 0.0D));
		}
		else
		{
			return Complex.ZERO;
		}
	}

	@Override
	public boolean areSampleCalculationParametersProvided()
	{
		return ObjectUtils.allNotNull(this.amplitude, this.initialTime, this.duration, this.amplitudeRiseTime);
	}
}
