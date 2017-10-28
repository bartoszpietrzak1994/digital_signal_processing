package model.signal.noise;

import java.util.Random;

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
		Random random = new Random();

		// value between 0.0 and 1.0
		double v = random.nextDouble();

		return v < valuePresenceProbability.getReal() ? amplitude : Complex.ZERO;
	}

	@Override
	public boolean areParametersProvided()
	{
		return ObjectUtils.allNotNull(this.amplitude, this.initialTime, this.duration, this.samplingRate, this.valuePresenceProbability);
	}
}
