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

		for (double iterator = initialTime.getReal(); iterator <= endTime.getReal(); iterator += samplingRate.getReal())
		{
			samples.add(new Complex(iterator, iterator));
		}

		return samples;
	}
}
