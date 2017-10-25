package model.signal.signal.triangle;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import exception.SignalParametersException;
import model.behaviour.ParameterType;
import model.signal.SignalType;
import model.signal.base.type.PeriodicSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
public class TriangleSignal extends PeriodicSignal
{
	public TriangleSignal()
	{
		super();
		this.signalType = SignalType.TRIANGLE_SIGNAL;
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE, ParameterType.PERIOD, ParameterType.DURATION, ParameterType.DUTY_CYCLE);
	}

	@Override
	public Complex calculate(Complex sample) throws SignalParametersException
	{
		return null;
	}

	@Override
	public boolean areParametersProvided()
	{
		return ObjectUtils.allNotNull(this.amplitude, this.period, this.initialTime, this.duration, this.dutyCycle);
	}
}
