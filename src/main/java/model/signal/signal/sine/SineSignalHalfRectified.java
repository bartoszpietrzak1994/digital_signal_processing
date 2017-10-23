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
public class SineSignalHalfRectified extends AbstractSineSignal
{
	public SineSignalHalfRectified(
			Complex amplitude,
			Complex initialTime,
			Complex duration,
			Complex period,
			Complex dutyCycle,
			Complex samplingRate,
			List<Complex> values)
	{
		super(amplitude, initialTime, duration, period, dutyCycle, samplingRate, values);
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
