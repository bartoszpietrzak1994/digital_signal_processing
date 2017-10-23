package model.signal.impulse;

import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import exception.SignalParametersException;
import lombok.NoArgsConstructor;
import model.behaviour.ParameterType;
import model.signal.SignalType;
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

		this.signalType = SignalType.UNIT_IMPULSE;
	}

	@Override
	public Complex calculate(Complex sample) throws SignalParametersException
	{
//		isCalculationValidForSignal(sample.keySet(), this.applicableParameters);
//
//		Complex amplitude = sample.get(ParameterType.AMPLITUDE);
//		Complex initialTime = sample.get(ParameterType.INITIAL_TIME);
//		Complex duration = sample.get(ParameterType.DURATION);
//		Complex amplitudeRiseTime = sample.get(ParameterType.AMPLITUDE_RISE_TIME);

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
