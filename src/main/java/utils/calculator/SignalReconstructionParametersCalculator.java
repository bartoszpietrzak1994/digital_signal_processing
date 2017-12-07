package utils.calculator;

import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import com.google.common.collect.Iterables;

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

	public Complex calculateMSE(Signal signal)
	{
		double result = 0;

		List<Complex> signalValues = signal.getValues();
		List<Complex> quantizationValues = signal.getQuantizationValues();

		for (int i = 0; i < signalValues.size(); i++)
		{
			result += (signalValues.get(i).getReal() - quantizationValues.get(i).getReal()) * (signalValues.get(i).getReal() - quantizationValues.get(i)
					.getReal());
		}

		return new Complex(result, 0.0D);
	}

	public Complex calculatePSNR(Signal signal)
	{
		List<Complex> signalValues = signal.getValues();
		List<Complex> quantizationValues = signal.getQuantizationValues();
		double maximum = Iterables.getFirst(signalValues, null).getReal();

		double currentValue;
		for (int i = 0; i < quantizationValues.size(); i++)
		{
			currentValue = quantizationValues.get(i).getReal();
			if (currentValue > maximum)
			{
				maximum = currentValue;
			}
		}

		return new Complex(10 * Math.log10(maximum / calculateMSE(signal).getReal()), 0.0D);
	}

	public Complex calculateMD(Signal signal)
	{
		List<Complex> signalValues = signal.getValues();
		List<Complex> quantizationValues = signal.getQuantizationValues();

		double maximum = Iterables.getFirst(signalValues, null).getReal() - Iterables.getFirst(quantizationValues, null).getReal();

		for (int i = 0; i < quantizationValues.size(); i++)
		{
			if ((signalValues.get(i).getReal() - quantizationValues.get(i).getReal()) > maximum)
			{
				maximum = signalValues.get(i).getReal() - quantizationValues.get(i).getReal();
			}
		}

		return new Complex(maximum, 0.0D);
	}
}
