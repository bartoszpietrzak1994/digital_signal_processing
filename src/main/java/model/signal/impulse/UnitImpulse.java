package model.signal.impulse;

import java.util.List;
import java.util.Map;

import org.apache.commons.math.complex.Complex;

import com.google.common.collect.Sets;

import model.behaviour.ParameterType;
import model.signal.base.periodic.NonPeriodicSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
public class UnitImpulse extends NonPeriodicSignal
{
	public UnitImpulse(
			double amplitude, double initialTime, double duration, double dutyCycle, Integer samplingRate, List<Complex> values)
	{
		super(amplitude, initialTime, duration, dutyCycle, samplingRate, values);
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE,
				ParameterType.INITIAL_TIME,
				ParameterType.DURATION,
				ParameterType.AMPLITUDE_RISE_TIME);
	}

	@Override
	public Complex calculate(Map<ParameterType, Complex> values)
	{
		// TODO Rewrite using aspectJ
		isCalculationValidForSignal(values.keySet(), this.applicableParameters);

		Complex amplitude = values.get(ParameterType.AMPLITUDE);
		Complex initialTime = values.get(ParameterType.INITIAL_TIME);
		Complex duration = values.get(ParameterType.DURATION);
		Complex amplitudeRiseTime = values.get(ParameterType.AMPLITUDE_RISE_TIME);

		// TODO
		//		if (duration > amplitudeRiseTime)
		//		{
		//			return amplitude;
		//		}
		//		else if (duration == amplitudeRiseTime)
		//		{
		//			return amplitude / 2.0;
		//		}
		//		else
		//		{
		//			return 0;
		//		}

		return null;
	}
}
