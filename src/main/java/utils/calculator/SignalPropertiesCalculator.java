package utils.calculator;

import lombok.Data;
import model.signal.AbstractSignal;

/**
 * Created by bartoszpietrzak on 06/10/2017.
 */
@Data
public class SignalPropertiesCalculator
{
	private AbstractSignal abstractSignal;

	public double calculateAaverageValue()
	{
		return 0;
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
