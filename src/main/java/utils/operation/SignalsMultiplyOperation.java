package utils.operation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import model.behaviour.SignalOperation;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 14/11/2017.
 */
@Component
public class SignalsMultiplyOperation extends SignalsOperationsCalculator
{
	public SignalsMultiplyOperation()
	{
		this.signalOperation = SignalOperation.MULTIPLY;
	}

	@Override
	public Signal calculate(Signal first, Signal second, Signal result) throws SignalParametersException
	{
		Complex initialTime = new Complex(Math.min(first.getInitialTime().getReal(), second.getInitialTime().getReal()), 0.0D);
		Complex endTime = new Complex(Math.max(first.getEndTime().getReal(), second.getInitialTime().getReal()), 0.0D);

		result.setInitialTime(initialTime);
		result.setEndTime(endTime);
		result.setDuration(endTime.subtract(initialTime));
		result.setSamplingRate(first.getSamplingRate());
		result.setSamples(signalValuesCalculator.getSampleList(result.getSamplingRate(), result.getInitialTime(), result.getEndTime()));

		Complex firstSample;
		Complex secondSample;
		List<Complex> values = new ArrayList<>();
		for (int i = 0; i < result.getSamples().size(); i++)
		{
			firstSample = Complex.ONE;
			secondSample = Complex.ONE;

			if (first.getSamples().contains(result.getSamples().get(i)))
			{
				firstSample = result.getSamples().get(i);
			}
			if (second.getSamples().contains(result.getSamples().get(i)))
			{
				secondSample = result.getSamples().get(i);
			}

			values.add(firstSample.multiply(secondSample));
		}

		result.setValues(values);

		return result;
	}
}
