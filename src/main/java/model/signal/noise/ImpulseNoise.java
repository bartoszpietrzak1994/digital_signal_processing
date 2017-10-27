package model.signal.noise;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import lombok.Getter;
import lombok.Setter;
import model.behaviour.ParameterType;
import model.signal.SignalType;
import model.signal.base.type.NonPeriodicSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
// TODO
public class ImpulseNoise extends NonPeriodicSignal
{
	public ImpulseNoise()
	{
		super();
		this.signalType = SignalType.IMPULSE_NOISE;
		this.applicableParameters = Sets.newHashSet(
				ParameterType.AMPLITUDE,
				ParameterType.INITIAL_TIME,
				ParameterType.DURATION,
				ParameterType.DISCRETIZATION_FREQUENCY,
				ParameterType.VALUE_PRESENCE_PROBABILITY);
	}

	@Override
	public Complex calculate(Complex sample)
	{
		return null;
	}

	@Override
	public boolean areParametersProvided()
	{
		return ObjectUtils.allNotNull(this.amplitude, this.initialTime, this.duration, this.samplingRate, this.valuePresenceProbability);
	}
}
