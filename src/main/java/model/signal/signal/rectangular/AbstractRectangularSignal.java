package model.signal.signal.rectangular;

import java.util.List;

import org.apache.commons.math.complex.Complex;

import com.google.common.collect.Sets;

import lombok.NoArgsConstructor;
import model.behaviour.ParameterType;
import model.signal.base.type.PeriodicSignal;

/**
 * Created by bartoszpietrzak on 16/10/2017.
 */
@NoArgsConstructor
public abstract class AbstractRectangularSignal extends PeriodicSignal
{
	public AbstractRectangularSignal(
			Complex amplitude,
			Complex initialTime,
			Complex duration,
			Complex period,
			Complex dutyCycle,
			Complex samplingRate,
			List<Complex> values)
	{
		super(amplitude, initialTime, duration, period, dutyCycle, samplingRate, values);
		this.applicableParameters = Sets.newHashSet(
				ParameterType.AMPLITUDE,
				ParameterType.PERIOD,
				ParameterType.INITIAL_TIME,
				ParameterType.DURATION,
				ParameterType.DUTY_CYCLE);
	}
}