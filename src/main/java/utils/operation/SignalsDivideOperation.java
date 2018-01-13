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
public class SignalsDivideOperation extends SignalsOperationsCalculator
{
    public SignalsDivideOperation()
    {
        this.signalOperation = SignalOperation.DIVIDE;
    }

    @Override
    public Signal calculate(Signal first, Signal second, Signal result) throws SignalParametersException
    {
        Complex initialTime = new Complex(Math.min(first.getInitialTime().getReal(), second.getInitialTime().getReal
				()), 0.0D);
        Complex endTime = new Complex(Math.max(first.getEndTime().getReal(), second.getInitialTime().getReal()), 0.0D);

        result.setInitialTime(initialTime);
        result.setEndTime(endTime);
        result.setDuration(endTime.subtract(initialTime));
        result.setSamplingRate(first.getSamplingRate());
        result.setSamples(signalValuesCalculator.getSampleList(result.getSamplingRate(), result.getInitialTime(),
				result.getEndTime()));
        result.setPeriod(first.getPeriod());

        Complex firstValue;
        Complex secondValue;
        List<Complex> values = new ArrayList<>();
        for (int i = 0; i < result.getSamples().size(); i++)
        {
            firstValue = Complex.ZERO;
            secondValue = Complex.ZERO;

            if (first.getSamples().contains(result.getSamples().get(i)))
            {
                int indexOf = first.getSamples().indexOf(result.getSamples().get(i));
                firstValue = first.getValues().get(indexOf);
            }
            if (second.getSamples().contains(result.getSamples().get(i)))
            {
                int indexOf = second.getSamples().indexOf(result.getSamples().get(i));
                secondValue = second.getValues().get(indexOf);
            }

            values.add(new Complex(firstValue.getReal() / secondValue.getReal(), 0.0D));
        }

        result.setValues(values);

        return result;
    }
}
