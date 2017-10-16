package model.signal.impulse;

import java.util.List;
import java.util.Map;

import org.apache.commons.math.complex.Complex;

import com.google.common.collect.Sets;

import exception.InvalidSignalParametersException;
import model.behaviour.ParameterType;
import model.signal.AbstractSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
public class UnitImpulse extends AbstractSignal
{
	public UnitImpulse(
			double amplitude,
			double initialTime,
			double duration,
			double period,
			Boolean isPeriodic,
			double dutyCycle,
			Integer samplingRate,
			List<Complex> values)
	{
		super(amplitude, initialTime, duration, period, isPeriodic, dutyCycle, samplingRate, values);
		this.applicableParameters = Sets.newHashSet(
				ParameterType.AMPLITUDE,
				ParameterType.INITIAL_TIME,
				ParameterType.DURATION,
				ParameterType.AMPLITUDE_RISE_TIME);
	}

	@Override
	public Complex calculate(Map<ParameterType, Complex> values)
	{
		// TODO Rewrite using aspectJ
		if (!isCalculationValidForSignal(values.keySet(), this.applicableParameters))
		{
			throw new InvalidSignalParametersException(
					"Applicable signal parameters: " + this.applicableParameters + " does not match with given: " + values.keySet());
		}

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
