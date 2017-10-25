package model.signal.signal.sine;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import model.signal.SignalType;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
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
//		isCalculationValidForSignal(sample.keySet(), this.applicableParameters);
//
//		Complex amplitude = sample.get(ParameterType.AMPLITUDE);
//		Complex period = sample.get(ParameterType.PERIOD);
//		Complex initialTime = sample.get(ParameterType.INITIAL_TIME);
//		Complex duration = sample.get(ParameterType.DURATION);
//
//		double real = amplitude.getReal() * Math.abs(Math.sin(((2.0 * Math.PI) / period.getReal()) * (duration.getReal() - initialTime.getReal())));
//		double imaginary = amplitude.getImaginary() * Math.abs(Math.sin(
//				((2.0 * Math.PI) / period.getImaginary()) * (duration.getImaginary() - initialTime.getImaginary())));
//
//		return new Complex(real, imaginary);

		return null;
	}
}
