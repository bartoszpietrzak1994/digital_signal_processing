package model.signal;

import org.apache.commons.math.complex.Complex;

import exception.SignalParametersException;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 14/11/2017.
 */
public class UnknownSignal extends Signal
{
	public UnknownSignal()
	{
		this.signalType = SignalType.UNKNOWN;
	}

	@Override
	public Complex calculate(Complex sample) throws SignalParametersException
	{
		throw new IllegalStateException("Unknown signal type is used only for calculations between two other signals");
	}

	@Override
	public boolean areSampleCalculationParametersProvided()
	{
		return true;
	}
}
