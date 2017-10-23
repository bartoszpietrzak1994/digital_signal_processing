package model.signal.base;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math.complex.Complex;

import exception.SignalParametersException;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.behaviour.ParameterType;
import model.signal.SignalType;

/**
 * Created by bartoszpietrzak on 06/10/2017.
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class Signal
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
	 * Measured in seconds
	 */
	protected Complex samplingRate;

	protected List<Complex> values;

	protected Set<ParameterType> applicableParameters;

	/**
	 * Calculated in SignalPropertiesCalculator
	 */
	protected Complex averageValue;
	protected Complex absoluteAverageValue;
	protected Complex signalPower;

	/**
	 * Calculated in SignalSamplesCalculator
	 */
	protected List<Complex> samples;

	public Signal(
			Complex amplitude,
			Complex initialTime,
			Complex duration,
			Complex dutyCycle,
			Complex samplingRate,
			List<Complex> values)
	{
		this.amplitude = amplitude;
		this.initialTime = (initialTime.getReal() < 0) ? new Complex(0.0D, 0.0D) : initialTime;
		this.duration = duration;
		this.dutyCycle = dutyCycle;
		this.samplingRate = (samplingRate.getReal()  <= 0) ? Complex.ONE : samplingRate;
		this.values = values;

		this.endTime = initialTime.add(duration);
	}

	public abstract Complex calculate(Map<ParameterType, Complex> values) throws SignalParametersException;

	protected void isCalculationValidForSignal(Set<ParameterType> signalParameters, Set<ParameterType> applicableParameters) throws SignalParametersException
	{
		if (!signalParameters.containsAll(applicableParameters))
		{
			throw SignalParametersException.calculationDataNotProvided(signalParameters, applicableParameters);
		}
	}
}
