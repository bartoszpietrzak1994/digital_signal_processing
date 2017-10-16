package model.signal.signal.sine;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math.complex.Complex;

import com.google.common.collect.Sets;

import exception.InvalidSignalParametersException;
import model.behaviour.ParameterType;
import model.signal.AbstractSignal;

/**
 * Created by bartoszpietrzak on 07/10/2017.
 */
public class SineSignal extends AbstractSignal
{
	private Set<ParameterType> applicableParameters;

	public SineSignal(
			double amplitude,
			double initialTime,
			double duration,
			double period,
			Boolean isPeriodic,
			double dutyCycle,
			Integer samplingRate,
			List<Complex> values)
	{
		super(amplitude, initialTime, duration, period, isPeriodic, dutyCycle, samplingRate, values);
		this.applicableParameters = Sets.newHashSet(ParameterType.AMPLITUDE, ParameterType.PERIOD, ParameterType.INITIAL_TIME, ParameterType.DURATION);
	}

	@Override
	public double calculate(Map<ParameterType, Double> values)
	{
		if (!isCalculationValidForSignal(values.keySet(), this.applicableParameters))
		{
			throw new InvalidSignalParametersException(
					"Applicable signal parameters: " + this.applicableParameters + " does not match with given: " + values.keySet());
		}

		double amplitude = values.get(ParameterType.AMPLITUDE);
		double period = values.get(ParameterType.PERIOD);
		double initialTime = values.get(ParameterType.INITIAL_TIME);
		double duration = values.get(ParameterType.DURATION);

		return amplitude * Math.sin(((2 * Math.PI) / period) * (duration - initialTime));
	}
}
