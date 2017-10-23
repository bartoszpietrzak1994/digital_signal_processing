package model.signal.impulse;

import java.util.List;
import java.util.Map;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import exception.SignalParametersException;
import lombok.NoArgsConstructor;
import model.behaviour.ParameterType;
import model.signal.base.type.NonPeriodicSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
@NoArgsConstructor
public class UnitImpulse extends NonPeriodicSignal
{
	public UnitImpulse(
			Complex amplitude, Complex initialTime, Complex duration, Complex dutyCycle, Complex samplingRate, List<Complex> values)
	{
		super(amplitude, initialTime, duration, dutyCycle, samplingRate, values);
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE,
				ParameterType.INITIAL_TIME,
				ParameterType.DURATION,
				ParameterType.AMPLITUDE_RISE_TIME);
	}

	@Override
	public Complex calculate(Map<ParameterType, Complex> values) throws SignalParametersException
	{
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
