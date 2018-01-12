package model.signal.signal.sine;

import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import model.signal.SignalType;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component(value = "SINE_SIGNAL_HALF_RECTIFIED")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SineSignalHalfRectified extends AbstractSineSignal
{
	public SineSignalHalfRectified()
	{
		super();
		this.signalType = SignalType.SINE_SIGNAL_HALF_RECTIFIED;
	}

	@Override
	public Complex calculate(Complex sample) throws SignalParametersException
	{
		double commonRealValue = Math.sin(((2.0 * Math.PI) / this.period.getReal()) * (sample.getReal() - this.initialTime.getReal()));
		double real = (1.0 / 2.0) * this.amplitude.getReal() * (commonRealValue + Math.abs(commonRealValue));

		return new Complex(real, 0.0D);
	}
}
