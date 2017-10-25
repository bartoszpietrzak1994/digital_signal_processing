package model.signal.signal.sine;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import model.signal.SignalType;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
public class SineSignalFullyRectified extends AbstractSineSignal
{
	public SineSignalFullyRectified()
	{
		super();
		this.signalType = SignalType.SINE_SIGNAL_FULLY_RECTIFIED;
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
//		double commonRealValue = ((2.0 * Math.PI) / period.getReal()) * (duration.getReal() - initialTime.getReal());
//		double real = (1.0 / 2.0) * amplitude.getReal() * (commonRealValue + Math.abs(commonRealValue));
//
//		double commonImaginaryValue = ((2.0 * Math.PI) / period.getImaginary()) * (duration.getImaginary() - initialTime.getImaginary());
//		double imaginary =  (1.0 / 2.0) * amplitude.getImaginary() * (commonImaginaryValue + Math.abs(commonImaginaryValue));
//
//		return new Complex(real, imaginary);

		return null;
	}
}
