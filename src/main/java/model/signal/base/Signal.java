package model.signal.base;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math.complex.Complex;

import model.behaviour.ParameterType;
import model.signal.SignalType;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
public interface Signal
{
	Complex calculate(Map<ParameterType, Complex> values);
	void isCalculationValidForSignal(Set<ParameterType> signalParameters, Set<ParameterType> applicableParameters);
	SignalType getSignalType();
	Set<ParameterType> getApplicableParameters();
	List<Complex> getValues();
	Integer getSamplingRate();
}
