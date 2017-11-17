package utils.calculator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import exception.SignalParametersException;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
@Component
public class SignalValuesCalculator
{
	public List<Complex> getSampleList(Complex samplingRate, Complex initialTime, Complex endTime)
	{
		List<Complex> samples = new ArrayList<>();

		double iterator = initialTime.getReal();
		double increment = endTime.subtract(initialTime).getReal() / samplingRate.getReal();

		while (iterator <= endTime.getReal())
		{
			samples.add(new Complex(iterator, 0.0D));
			iterator += increment;
		}

		return samples;
	}

	public List<Complex> calculateSignalValues(Signal signal) throws SignalParametersException
	{
		List<Complex> samples = signal.getSamples();

		if (CollectionUtils.isEmpty(samples))
		{
			throw SignalParametersException.unableToCalculateSignalValues();
		}

		List<Complex> values = new ArrayList<>();

		for (int i = 0; i < samples.size(); i++)
		{
			values.add(signal.calculate(samples.get(i)));
		}

		return values;
	}
}
