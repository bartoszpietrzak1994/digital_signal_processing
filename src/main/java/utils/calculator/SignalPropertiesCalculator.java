package utils.calculator;

import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import com.google.common.collect.Iterables;

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
	public Complex calculateAverageValue(Signal signal)
	{
		List<Complex> samples = signal.getSamples();
		List<Complex> values = signal.getValues();

		Complex firstSample = Iterables.getFirst(samples, null);
		Complex lastSample = Iterables.getLast(samples, null);

		assert firstSample != null;
		assert lastSample != null;

		Complex sum = Complex.ZERO;

		for (int i = 0; i < samples.size(); i++)
		{
			sum  = sum.add(values.get(i));
		}

		return Complex.ONE.divide(lastSample.subtract(firstSample).add(Complex.ONE)).multiply(sum);
	}

	public Complex calculateAbsoluteAverageValue(Signal signal)
	{
		List<Complex> samples = signal.getSamples();
		List<Complex> values = signal.getValues();

		Complex firstSample = Iterables.getFirst(samples, null);
		Complex lastSample = Iterables.getLast(samples, null);

		assert firstSample != null;
		assert lastSample != null;

		Complex sum = Complex.ZERO;

		for (int i = 0; i < samples.size(); i++)
		{
			Complex value = values.get(i);
			sum = sum.add(new Complex(Math.abs(value.getReal()), Math.abs(value.getImaginary())));
		}

		return Complex.ONE.divide(lastSample.subtract(firstSample).add(Complex.ONE)).multiply(sum);
	}

	public Complex calculateSignalPower(Signal signal)
	{
		List<Complex> samples = signal.getSamples();
		List<Complex> values = signal.getValues();

		Complex firstSample = Iterables.getFirst(samples, null);
		Complex lastSample = Iterables.getLast(samples, null);

		assert firstSample != null;
		assert lastSample != null;

		double sum = 0;

		for (int i = 0; i < samples.size(); i++)
		{
			sum += values.get(i).getReal() * values.get(i).getReal();
		}

		return Complex.ONE.divide(lastSample.subtract(firstSample).add(Complex.ONE)).multiply(sum);
	}

	public Complex calculateVariance(Signal signal)
	{
		if (signal.getAverageValue() == null)
		{
			throw new IllegalStateException("Before calculating variance, signal's average values has to be set.");
		}

		Complex averageValue = signal.getAverageValue();

		List<Complex> samples = signal.getSamples();
		List<Complex> values = signal.getValues();

		Complex firstSample = Iterables.getFirst(samples, null);
		Complex lastSample = Iterables.getLast(samples, null);

		assert firstSample != null;
		assert lastSample != null;

		double sum = 0;

		for (int i = 0; i < samples.size(); i++)
		{
			sum += (values.get(i).getReal() - averageValue.getReal()) * (values.get(i).getReal() - averageValue.getReal());
		}

		return Complex.ONE.divide(lastSample.subtract(firstSample).add(Complex.ONE)).multiply(sum);
	}

	public Complex calculateRootMeanSquareValue(Signal signal)
	{
		return this.calculateSignalPower(signal).sqrt();
	}
}
