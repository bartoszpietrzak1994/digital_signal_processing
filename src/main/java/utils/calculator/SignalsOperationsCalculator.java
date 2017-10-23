package utils.calculator;

import model.behaviour.SignalOperation;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
public class SignalsOperationsCalculator
{
	public Signal performCalculation(Signal signalOne, Signal signalTwo, String operationName)
	{
		if (signalOne.getSamplingRate().compareTo(signalTwo.getSamplingRate()) != 0)
		{
			throw new IllegalStateException("Sampling rates of signals do not match! Aborting calculation.");
		}

		Signal signal = null;
		SignalOperation operation = SignalOperation.valueOf(operationName);

		switch (operation)
		{
			case ADD:
			{
				signal = addSignals(signalOne, signalTwo);
				break;
			}
			case SUBTRACT:
			{
				signal = subtractSignals(signalOne, signalTwo);
				break;
			}
			case MULTIPLY:
			{
				signal = multiplySignals(signalOne, signalTwo);
				break;
			}
			case DIVIDE:
			{
				signal = divideSignals(signalOne, signalTwo);
				break;
			}
		}

		return signal;
	}

	private Signal addSignals(Signal signalOne, Signal signalTwo)
	{
		return null;
	}

	private Signal subtractSignals(Signal signalOne, Signal signalTwo)
	{
		return null;
	}

	private Signal multiplySignals(Signal signalOne, Signal signalTwo)
	{
		return null;
	}

	private Signal divideSignals(Signal signalOne, Signal signalTwo)
	{
		return null;
	}
}
