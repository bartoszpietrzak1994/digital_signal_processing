package model.result;

import lombok.Data;

/**
 * Created by bartoszpietrzak on 06/10/2017.
 */
@Data
public class SignalResultParameters
{
	private double averageValue;
	private double absoluteAverageValue;
	private double rootMeanSquareValue;
	private double variance;
	private double signalPower;
}
