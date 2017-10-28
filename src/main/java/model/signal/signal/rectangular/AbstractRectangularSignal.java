package model.signal.signal.rectangular;

import org.apache.commons.lang3.ObjectUtils;

import com.google.common.collect.Sets;

import model.behaviour.ParameterType;
import model.signal.base.type.PeriodicSignal;

/**
 * Created by bartoszpietrzak on 16/10/2017.
 */
public abstract class AbstractRectangularSignal extends PeriodicSignal
{
	public AbstractRectangularSignal()
	{
		super();
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE, ParameterType.INITIAL_TIME, ParameterType.DURATION, ParameterType.DUTY_CYCLE);
	}

	@Override
	public boolean areSampleCalculationParametersProvided()
	{
		return ObjectUtils.allNotNull(this.amplitude, this.period, this.initialTime, this.duration, this.dutyCycle);
	}
}