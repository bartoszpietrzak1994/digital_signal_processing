package utils.calculator;

import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import lombok.Data;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 06/10/2017.
 */
@Data
@Component
public class SignalPropertiesCalculator
{
	public Complex calculateAaverageValue(Signal signal)
	{
		List<Complex> samples = signal.getSamples();
		List<Complex> values = signal.getValues();

		Complex initialTime = signal.getInitialTime();
		Complex endTime = signal.getEndTime();

		Complex sum = Complex.ZERO;

		for (int i = 0; i < samples.size(); i++)
		{
			sum.add(values.get(i));
		}

		return Complex.ONE.divide(endTime.subtract(initialTime).add(Complex.ONE)).multiply(sum);
	}

	public Complex calculateAbsolutetAverageValue(Signal signal)
	{
		List<Complex> samples = signal.getSamples();
		List<Complex> values = signal.getValues();

		Complex initialTime = signal.getInitialTime();
		Complex endTime = signal.getEndTime();

		Complex sum = Complex.ZERO;

		for (int i = 0; i < samples.size(); i++)
		{
			Complex value = values.get(i);
			sum.add(new Complex(Math.abs(value.getReal()), Math.abs(value.getImaginary())));
		}

		return Complex.ONE.divide(endTime.subtract(initialTime).add(Complex.ONE)).multiply(sum);
	}

	public Complex calculateSignalPower(Signal signal)
	{
		List<Complex> samples = signal.getSamples();
		List<Complex> values = signal.getValues();

		Complex initialTime = signal.getInitialTime();
		Complex endTime = signal.getEndTime();

		Complex sum = Complex.ZERO;
		Complex two = new Complex(2.0D, 2.0D);

		for (int i = 0; i < samples.size(); i++)
		{
			sum.add(values.get(i)).pow(two);
		}

		return Complex.ONE.divide(endTime.subtract(initialTime).add(Complex.ONE)).multiply(sum);
	}

	public Complex calculateVariance(Signal signal, Complex averageValue)
	{
		List<Complex> samples = signal.getSamples();
		List<Complex> values = signal.getValues();

		Complex initialTime = signal.getInitialTime();
		Complex endTime = signal.getEndTime();

		Complex sum = Complex.ZERO;
		Complex two = new Complex(2.0D, 2.0D);

		for (int i = 0; i < samples.size(); i++)
		{
			sum.add((values.get(i).subtract(averageValue)).pow(two));
		}

		return Complex.ONE.divide(endTime.subtract(initialTime).add(Complex.ONE)).multiply(sum);
	}

	public Complex calculateRootMeanSquareValue(Signal signal)
	{
		return this.calculateSignalPower(signal).sqrt();
	}
}
