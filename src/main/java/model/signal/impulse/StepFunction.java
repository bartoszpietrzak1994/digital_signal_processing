package model.signal.impulse;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import model.behaviour.ParameterType;
import model.signal.SignalType;
import model.signal.base.type.NonPeriodicSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component(value = "STEP_FUNCTION")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
			return new Complex(this.amplitude.getReal() / 2.0, 0.0D);
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
