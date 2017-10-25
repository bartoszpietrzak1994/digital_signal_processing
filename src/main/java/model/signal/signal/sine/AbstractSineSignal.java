package model.signal.signal.sine;

import org.apache.commons.lang3.ObjectUtils;

import com.google.common.collect.Sets;

import lombok.NoArgsConstructor;
import model.behaviour.ParameterType;
import model.signal.base.type.PeriodicSignal;

/**
 * Created by bartoszpietrzak on 16/10/2017.
 */
public abstract class AbstractSineSignal extends PeriodicSignal
{
	public AbstractSineSignal()
	{
		super();
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE, ParameterType.PERIOD, ParameterType.INITIAL_TIME, ParameterType.DURATION);
	}

	@Override
	public boolean areParametersProvided()
	{
		return ObjectUtils.allNotNull(this.amplitude, this.period, this.initialTime, this.duration);
	}
}
