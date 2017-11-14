package utils.operation;

import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import model.behaviour.SignalOperation;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 14/11/2017.
 */
@Component
public class SignalsAddOperation extends SignalsOperationsCalculator
{
	public SignalsAddOperation()
	{
		this.signalOperation = SignalOperation.ADD;
	}

	@Override
	public Signal calculate(Signal first, Signal second, Signal result) throws SignalParametersException
	{
		for (int i = 0; i < first.getValues().size(); i++)
		{
			result.getValues().add(first.getValues().get(i).add(second.getValues().get(i)));
		}

		return result;
	}
}
