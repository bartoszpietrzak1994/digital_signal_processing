package model.signal.base;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exception.InvalidSignalParametersException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import model.behaviour.ParameterType;
import model.signal.SignalType;

/**
 * Created by bartoszpietrzak on 06/10/2017.
 */
@Getter
@NoArgsConstructor
public abstract class AbstractSignal implements Signal
{
	protected SignalType signalType;

	protected Complex amplitude;

	/**
	 * Miliseconds
	 */
	protected Complex initialTime;
	protected Complex duration;
	/**
	 * endTime = initialTime + duration
	 */
	protected Complex endTime;

	/**
	 * If isPeriodic == false, period should bo equal to 0
	 * If isPeriodic == null, we assume that it should be false
	 */
	protected Complex period;
	protected Boolean isPeriodic;

	protected Complex dutyCycle;

	/**
	 * Measured in Hz
	 */
	protected Integer samplingRate;

	protected List<Complex> values;

	protected Set<ParameterType> applicableParameters;

	@Override
	public SignalType getSignalType()
	{
		return this.signalType;
	}

	@Override
	public Set<ParameterType> getApplicableParameters()
	{
		return this.applicableParameters;
	}

	@Override
	public Integer getSamplingRate()
	{
		return this.samplingRate;
	}

	@Override
	public List<Complex> getValues()
	{
		return this.values;
	}

	@Override
	public void isCalculationValidForSignal(Set<ParameterType> signalParameters, Set<ParameterType> applicableParameters)
	{
		if (!signalParameters.containsAll(applicableParameters))
		{
			throw new InvalidSignalParametersException(
					"Applicable signal parameters: " + applicableParameters + " does not match with given: " + signalParameters);
		}
	}

	public AbstractSignal(
			Complex amplitude,
			Complex initialTime,
			Complex duration,
			Complex dutyCycle,
			Integer samplingRate,
			List<Complex> values)
	{
		this.amplitude = amplitude;
		this.initialTime = (initialTime.getReal() < 0) ? new Complex(0, 0) : initialTime;
		this.duration = duration;
		this.dutyCycle = dutyCycle;
		this.samplingRate = (samplingRate == null || samplingRate  <= 0) ? 1 : samplingRate;
		this.values = values;

		this.endTime = initialTime.add(duration);
	}
}
