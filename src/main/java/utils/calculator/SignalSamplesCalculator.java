package utils.calculator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
@Component
public class SignalSamplesCalculator
{
	public List<Complex> getSampleList(Complex samplingRate, Complex initialTime, Complex endTime)
	{
		List<Complex> samples = new ArrayList<>();

		double iterator = initialTime.getReal();

		while (iterator <= endTime.getReal())
		{
			samples.add(new Complex(iterator, 0.0D));
			iterator += 1.0/samplingRate.getReal();
		}

		return samples;
	}
}
