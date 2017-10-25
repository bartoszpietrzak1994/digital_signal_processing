package model.signal.impulse;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import exception.SignalParametersException;
import model.behaviour.ParameterType;
import model.signal.SignalType;
import model.signal.base.type.NonPeriodicSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
public class UnitImpulse extends NonPeriodicSignal
{
	public UnitImpulse()
	{
		super();
		this.signalType = SignalType.UNIT_IMPULSE;
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE,
				ParameterType.INITIAL_TIME,
				ParameterType.DURATION,
				ParameterType.AMPLITUDE_RISE_TIME);
	}

	@Override
	public boolean areParametersProvided()
	{
		return ObjectUtils.anyNotNull(this.amplitude, this.initialTime, this.duration, this.amplitudeRiseTime);
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
