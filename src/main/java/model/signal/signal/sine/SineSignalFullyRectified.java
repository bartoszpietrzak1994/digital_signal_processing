package model.signal.signal.sine;

import java.util.List;
import java.util.Map;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import lombok.NoArgsConstructor;
import model.behaviour.ParameterType;

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
	}

	@Override
	public Complex calculate(Map<ParameterType, Complex> values) throws SignalParametersException
	{
		isCalculationValidForSignal(values.keySet(), this.applicableParameters);

		Complex amplitude = values.get(ParameterType.AMPLITUDE);
		Complex period = values.get(ParameterType.PERIOD);
		Complex initialTime = values.get(ParameterType.INITIAL_TIME);
		Complex duration = values.get(ParameterType.DURATION);

		double commonRealValue = ((2.0 * Math.PI) / period.getReal()) * (duration.getReal() - initialTime.getReal());
		double real = (1.0 / 2.0) * amplitude.getReal() * (commonRealValue + Math.abs(commonRealValue));

		double commonImaginaryValue = ((2.0 * Math.PI) / period.getImaginary()) * (duration.getImaginary() - initialTime.getImaginary());
		double imaginary =  (1.0 / 2.0) * amplitude.getImaginary() * (commonImaginaryValue + Math.abs(commonImaginaryValue));

		return new Complex(real, imaginary);
	}
}
