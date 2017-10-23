package utils.calculator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
@Component
public class SamplesCalculator
{
	public List<Complex> getSampleList(Signal signal)
	{
		Complex samplingRate = signal.getSamplingRate();
		Complex initialTime = signal.getInitialTime();
		Complex endTime = signal.getEndTime();

		List<Complex> samples = new ArrayList<>();

		for (double iterator = initialTime.getReal(); iterator <= endTime.getReal(); iterator += samplingRate.getReal())
		{
			samples.add(new Complex(iterator, iterator));
		}

		return samples;
	}
}
