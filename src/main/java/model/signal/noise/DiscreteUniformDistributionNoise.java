package model.signal.noise;

import java.util.List;
import java.util.Map;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import lombok.NoArgsConstructor;
import model.behaviour.ParameterType;
import model.signal.base.type.NonPeriodicSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
@NoArgsConstructor
public class DiscreteUniformDistributionNoise extends NonPeriodicSignal
{
	public DiscreteUniformDistributionNoise(
			Complex amplitude,
			Complex initialTime,
			Complex duration,
			Complex dutyCycle,
			Complex samplingRate,
			List<Complex> values)
	{
		super(amplitude, initialTime, duration, dutyCycle, samplingRate, values);
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE, ParameterType.INITIAL_TIME, ParameterType.DURATION);
	}

	@Override
	public Complex calculate(Map<ParameterType, Complex> values)
	{
		return null;
	}
}
