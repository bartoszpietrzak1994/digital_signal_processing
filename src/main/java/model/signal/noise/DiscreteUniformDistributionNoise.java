package model.signal.noise;

import java.util.Random;

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
@Component(value = "DISCRETE_UNIFORM_DISTRIBUTION_NOISE")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
		Complex negatedAmplitude = Complex.ONE.negate().multiply(this.amplitude);
		Random random = new Random();

		return negatedAmplitude.add(this.amplitude.subtract(negatedAmplitude).multiply(new Complex(random.nextDouble(), 0.0D)));
	}

	@Override
	public boolean areSampleCalculationParametersProvided()
	{
		return ObjectUtils.allNotNull(this.amplitude, this.initialTime, this.duration);
	}
}