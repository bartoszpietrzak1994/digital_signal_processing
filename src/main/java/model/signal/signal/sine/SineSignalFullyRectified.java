package model.signal.signal.sine;

import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import lombok.NoArgsConstructor;
import model.behaviour.ParameterType;
import model.signal.SignalType;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
@NoArgsConstructor
public class SineSignalFullyRectified extends AbstractSineSignal
{
	public SineSignalFullyRectified(
			Complex amplitude,
			Complex initialTime,
			Complex duration,
			Complex period,
			Complex dutyCycle,
			Complex samplingRate,
			List<Complex> values)
	{
		super(amplitude, initialTime, duration, period, dutyCycle, samplingRate, values);
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
