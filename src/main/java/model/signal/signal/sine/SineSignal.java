package model.signal.signal.sine;

import java.util.List;
import java.util.Map;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import model.behaviour.ParameterType;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
public class SineSignal extends AbstractSineSignal
{
	public SineSignal(
			Complex amplitude,
			Complex initialTime,
			Complex duration,
			Complex period,
			Complex dutyCycle,
			Integer samplingRate,
			List<Complex> values)
	{
		super(amplitude, initialTime, duration, period, dutyCycle, samplingRate, values);
	}

	@Override
	public Complex calculate(Map<ParameterType, Complex> values)
	{
		isCalculationValidForSignal(values.keySet(), this.applicableParameters);

		Complex amplitude = values.get(ParameterType.AMPLITUDE);
		Complex period = values.get(ParameterType.PERIOD);
		Complex initialTime = values.get(ParameterType.INITIAL_TIME);
		Complex duration = values.get(ParameterType.DURATION);

		double real = amplitude.getReal() * Math.sin(((2.0 * Math.PI) / period.getReal()) * (duration.getReal() - initialTime.getReal()));
		double imaginary =
				amplitude.getImaginary() * Math.sin(((2.0 * Math.PI) / period.getImaginary()) * (duration.getImaginary() - initialTime.getImaginary()));

		return new Complex(real, imaginary);
	}
}
