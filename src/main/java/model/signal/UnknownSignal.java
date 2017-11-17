package model.signal;

import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 14/11/2017.
 */
@Component(value = "UNKNOWN")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
