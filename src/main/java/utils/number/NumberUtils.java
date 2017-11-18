package utils.number;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by bartoszpietrzak on 18/11/2017.
 */
public class NumberUtils
{
	public static Double formatDouble(double number, int decimalPlaces)
	{
		return BigDecimal.valueOf(number)
				.setScale(decimalPlaces, RoundingMode.HALF_UP)
				.doubleValue();
	}
}
