package model.signal.base.type;

import java.util.List;

import org.apache.commons.math.complex.Complex;

import lombok.NoArgsConstructor;
import model.signal.base.AbstractSignal;

/**
 * Created by bartoszpietrzak on 19/10/2017.
 */
@NoArgsConstructor
public abstract class NonPeriodicSignal extends AbstractSignal
{
	public NonPeriodicSignal(
			Complex amplitude, Complex initialTime, Complex duration, Complex dutyCycle, Integer samplingRate, List<Complex> values)
	{
		super(amplitude, initialTime, duration, dutyCycle, samplingRate, values);
		this.isPeriodic = Boolean.FALSE;
		this.period = new Complex(0, 0);
	}
}
