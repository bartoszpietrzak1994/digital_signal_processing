package model.response;

import org.apache.commons.math.complex.Complex;

import lombok.Builder;
import lombok.Data;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 27/10/2017.
 */
@Data
@Builder
public class SignalPropertiesCalculationResponse
{
	private Signal signal;
	private Complex averageSignalValue;
	private Complex absoluteAverageSignalValue;
	private Complex signalPower;
	private Complex signalVariance;
	private Complex signalRootMeanSquareValue;
}
