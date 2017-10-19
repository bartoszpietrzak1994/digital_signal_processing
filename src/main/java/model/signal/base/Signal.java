package model.signal.base;

import java.util.Map;
import java.util.Set;

import org.apache.commons.math.complex.Complex;

import exception.InvalidSignalParametersException;
import model.behaviour.ParameterType;

/**
 * Created by bartoszpietrzak on 19/10/2017.
 */
public interface Signal
{
	Complex calculate(Map<ParameterType, Complex> values);

	default void isCalculationValidForSignal(Set<ParameterType> signalParameters, Set<ParameterType> applicableParameters)
	{
		if (!signalParameters.containsAll(applicableParameters))
		{
			throw new InvalidSignalParametersException(
					"Applicable signal parameters: " + applicableParameters + " does not match with given: " + signalParameters);
		}
	}
}
