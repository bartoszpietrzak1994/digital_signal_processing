package utils.calculator;

import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 07/12/2017.
 */
@Component
public class SignalReconstructionParametersCalculator
{
	public Complex calculateSNR(Signal signal)
	{
		double sum1 = 0;
		double sum2 = 0;

		List<Complex> signalValues = signal.getValues();
		List<Complex> quantizationValues = signal.getQuantizationValues();
		double currentRealValue;
		double currentQuantValue;

		for (int i = 0; i < signalValues.size(); i++)
		{
			currentRealValue = signalValues.get(i).getReal();
			sum1 += currentRealValue * currentRealValue;
		}

		for (int i = 0; i < signalValues.size(); i++)
		{
			currentRealValue = signalValues.get(i).getReal();
			currentQuantValue = quantizationValues.get(i).getReal();

			sum2 += (currentRealValue - currentQuantValue) * (currentRealValue - currentQuantValue);
		}

		return new Complex(10 * Math.log10(sum1 / sum2), 0.0D);
	}
}
