package utils.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import lombok.Getter;
import model.behaviour.SignalOperation;
import model.signal.SignalType;
import model.signal.base.Signal;
import utils.calculator.SignalValuesCalculator;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
@Component
@Getter
public abstract class SignalsOperationsCalculator
{
	@Autowired
	protected SignalValuesCalculator signalValuesCalculator;

	protected SignalOperation signalOperation;

	public abstract Signal calculate(Signal first, Signal second, Signal result) throws SignalParametersException;

	public boolean shouldCalculationBePerformed(Signal first, Signal second)
	{
		return new Double(first.getSamplingRate().getReal()).compareTo(second.getSamplingRate().getReal()) == 0;
	}

	public SignalType resolveSignalType(SignalType first, SignalType second)
	{
		return first.equals(second) ? first : SignalType.UNKNOWN;
	}
}
