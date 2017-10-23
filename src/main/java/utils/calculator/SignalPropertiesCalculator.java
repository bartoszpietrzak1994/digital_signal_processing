package utils.calculator;

import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Iterables;

import exception.CalculationDataNotProvidedException;
import lombok.Data;
import model.signal.base.AbstractSignal;

/**
 * Created by bartoszpietrzak on 06/10/2017.
 */
@Data
public class SignalPropertiesCalculator
{
	private AbstractSignal signal;

	public Complex calculateAaverageValue()
	{
		List<Complex> values = signal.getValues();
		Complex first = Iterables.getFirst(values, null);
		Complex last = Iterables.getLast(values, null);

		if (CollectionUtils.isEmpty(values) || (first == null || last == null))
		{
			throw new CalculationDataNotProvidedException("Values for calculation were not provided. Skipping calculation.");
		}

		Complex sum = Complex.ZERO;

		for (Complex value : values)
		{
//			sum.add(signal.calculate() value);
		}

		return null;
	}

	public double calculateAbsolutetAverageValue()
	{
		return 0;
	}

	public double calculateSignalPower()
	{
		return 0;
	}

	// TODO wariancja sygnału w przedziale wokół wartości średniej ??
	public double calculateVariance()
	{
		return 0;
	}

	public double calculateRootMeanSquareValue()
	{
		return 0;
	}
}
