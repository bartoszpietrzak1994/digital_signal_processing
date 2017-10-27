package model.signal.noise;

import java.util.Random;

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
public class DiscreteUniformDistributionNoise extends NonPeriodicSignal
{
	public DiscreteUniformDistributionNoise()
	{
		this.signalType = SignalType.DISCRETE_UNIFORM_DISTRIBUTION_NOISE;
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE, ParameterType.INITIAL_TIME, ParameterType.DURATION);
	}

	@Override
	public Complex calculate(Complex sample)
	{
		double lowerBound = (-1.0D) * this.amplitude.getReal();
		double upperBound = this.amplitude.getReal();

		Random random = new Random();
		return new Complex(lowerBound + (upperBound - lowerBound) * random.nextDouble(), 0.0D);
	}

	@Override
	public boolean areParametersProvided()
	{
		return ObjectUtils.allNotNull(this.amplitude, this.initialTime, this.duration);
	}
}
