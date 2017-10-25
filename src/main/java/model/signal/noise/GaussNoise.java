package model.signal.noise;

import java.util.Set;

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
public class GaussNoise extends NonPeriodicSignal
{
	public GaussNoise()
	{
		this.signalType = SignalType.GAUSS_NOISE;
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE, ParameterType.INITIAL_TIME, ParameterType.DURATION);
	}

	@Override
	public Complex calculate(Complex sample)
	{
		return null;
	}

	@Override
	public boolean areParametersProvided()
	{
		return ObjectUtils.allNotNull(this.amplitude, this.initialTime, this.duration);
	}
}
