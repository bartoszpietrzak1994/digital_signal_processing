package model.signal.signal.triangle;

import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import exception.SignalParametersException;
import lombok.NoArgsConstructor;
import model.behaviour.ParameterType;
import model.signal.SignalType;
import model.signal.base.type.PeriodicSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
@NoArgsConstructor
public class TriangleSignal extends PeriodicSignal
{
	public TriangleSignal(
			Complex amplitude,
			Complex initialTime,
			Complex duration,
			Complex period,
			Complex dutyCycle,
			Complex samplingRate,
			List<Complex> values)
	{
		super(amplitude, initialTime, duration, period, dutyCycle, samplingRate, values);
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE,
				ParameterType.PERIOD,
				ParameterType.INITIAL_TIME,
				ParameterType.DURATION,
				ParameterType.DUTY_CYCLE);

		this.signalType = SignalType.TRIANGLE_SIGNAL;
	}

	@Override
	public Complex calculate(Complex sample) throws SignalParametersException
	{
		return null;
	}
}
