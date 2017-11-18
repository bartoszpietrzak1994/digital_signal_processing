package model.signal.impulse;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import exception.SignalParametersException;
import model.behaviour.ParameterType;
import model.signal.SignalType;
import model.signal.base.type.NonPeriodicSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component(value = "UNIT_IMPULSE")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UnitImpulse extends NonPeriodicSignal
{
	public UnitImpulse()
	{
		super();
		this.signalType = SignalType.UNIT_IMPULSE;
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE,
				ParameterType.INITIAL_TIME,
				ParameterType.DURATION,
				ParameterType.AMPLITUDE_RISE_SAMPLE);
	}

	@Override
	public Complex calculate(Complex sample) throws SignalParametersException
	{
		int index = this.samples.indexOf(sample);
		return index == this.amplitudeRiseSample.getReal() ? this.amplitude : Complex.ZERO;
	}

	@Override
	public boolean areSampleCalculationParametersProvided()
	{
		return ObjectUtils.anyNotNull(this.amplitude, this.initialTime, this.duration, this.amplitudeRiseSample);
	}
}
