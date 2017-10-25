package model.signal.signal.rectangular;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import model.signal.SignalType;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
public class RectangularSignal extends AbstractRectangularSignal
{
	public RectangularSignal()
	{
		super();
		this.signalType = SignalType.RECTANGULAR_SIGNAL;
	}

	@Override
	public Complex calculate(Complex sample)
	{
		return null;
	}
}
