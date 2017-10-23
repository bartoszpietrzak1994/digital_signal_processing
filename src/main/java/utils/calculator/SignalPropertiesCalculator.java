package utils.calculator;

import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Iterables;

import exception.CalculationDataNotProvidedException;
import lombok.Data;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 06/10/2017.
 */
@Data
public class SignalPropertiesCalculator
{
	private Signal signal;

	public Complex calculateAaverageValue()
	{
		List<Complex> values = signal.getValues();

		Complex initialTime = signal.getInitialTime();
		Complex endTime = signal.getEndTime();

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
