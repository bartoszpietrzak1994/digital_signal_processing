package model.signal.base;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import exception.InvalidSignalParametersException;
import lombok.Getter;
import model.behaviour.ParameterType;
import model.signal.SignalType;

/**
 * Created by bartoszpietrzak on 06/10/2017.
 */
@Getter
@Component
public abstract class Signal
{
	protected SignalType signalType;

	protected double amplitude;

	/**
	 * Miliseconds
	 */
	protected double initialTime;
	protected double duration;
	/**
	 * endTime = initialTime + duration
	 */
	protected double endTime;

	/**
	 * If isPeriodic == false, period should bo equal to 0
	 * If isPeriodic == null, we assume that it should be false
	 */
	protected double period;
	protected Boolean isPeriodic;

	protected double dutyCycle;

	/**
	 * Measured in Hz
	 */
	protected Integer samplingRate;

	protected List<Complex> values;

	protected Set<ParameterType> applicableParameters;

	public abstract Complex calculate(Map<ParameterType, Complex> values);

	protected void isCalculationValidForSignal(Set<ParameterType> signalParameters, Set<ParameterType> applicableParameters)
	{
		if (!signalParameters.containsAll(applicableParameters))
		{
			throw new InvalidSignalParametersException(
					"Applicable signal parameters: " + applicableParameters + " does not match with given: " + signalParameters);
		}
	}

	public Signal(
			double amplitude,
			double initialTime,
			double duration,
			double dutyCycle,
			Integer samplingRate,
			List<Complex> values)
	{
		this.amplitude = amplitude;
		this.initialTime = (initialTime < 0) ? initialTime = 0 : initialTime;
		this.duration = duration;
		this.dutyCycle = dutyCycle;
		this.samplingRate = (samplingRate == null || samplingRate  <= 0) ? 1 : samplingRate;
		this.values = values;

		this.endTime = initialTime + duration;
	}
}
