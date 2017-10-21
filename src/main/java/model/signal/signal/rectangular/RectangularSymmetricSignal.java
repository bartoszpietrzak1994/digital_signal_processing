package model.signal.signal.rectangular;

import java.util.List;
import java.util.Map;

import org.apache.commons.math.complex.Complex;

import model.behaviour.ParameterType;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
public class RectangularSymmetricSignal extends AbstractRectangularSignal
{
	public RectangularSymmetricSignal(
			Complex amplitude, Complex initialTime, Complex duration, Complex period, Complex dutyCycle, Integer samplingRate, List<Complex> values)
	{
		super(amplitude, initialTime, duration, period, dutyCycle, samplingRate, values);
	}

	@Override
	public Complex calculate(Map<ParameterType, Complex> values)
	{
		return null;
	}
}
