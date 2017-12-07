package model.quantization;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import com.google.common.collect.Iterables;

import model.behaviour.QuantizationType;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 07/12/2017.
 */
@Component
public class RoundingSignalQuantization extends SignalQuantization
{
	public RoundingSignalQuantization()
	{
		super();
		this.quantizationType = QuantizationType.ROUNDING;
	}

	@Override
	public Signal signalQuantization(Signal signal, Complex quantLevel)
	{
		List<Complex> quantizationValues = new ArrayList<>();

		quantizationValues.add(Iterables.getFirst(signal.getValues(), null));

		List<Complex> signalValues = signal.getValues();
		Complex quantizationValue;
		for (int i = 1; i < signalValues.size(); i++)
		{
			quantizationValue = signalValues.get(i);
			if (signalValues.get(i).getReal() > (quantizationValues.get(i - 1).getReal() + quantLevel.getReal()))
			{
				quantizationValue = signalValues.get(i).add(quantLevel);
			}
			else if (signalValues.get(i).getReal() <= (quantizationValues.get(i - 1).getReal() - quantLevel.getReal()))
			{
				quantizationValue = signalValues.get(i).subtract(quantLevel);
			}
			else
			{
				quantizationValue = quantizationValues.get(i -1);
			}

			quantizationValues.add(quantizationValue);
		}

		signal.setQuantizationValues(quantizationValues);

		return signal;
	}
}
