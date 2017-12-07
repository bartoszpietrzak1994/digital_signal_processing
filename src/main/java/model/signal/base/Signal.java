package model.signal.base;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.apache.commons.math.complex.Complex;

import com.google.gson.annotations.Expose;

import exception.SignalParametersException;
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
public abstract class Signal implements Serializable
{
	/**
	 * Set when putting signal in a signalRepository
	 */
	protected transient String id;

	protected SignalType signalType;

	protected Complex amplitude;

	/**
	 * Given as decimal between 0.0 and 1.0
	 */
	protected Complex valuePresenceProbability;

	protected Complex amplitudeRiseTime;

	protected Complex amplitudeRiseSample;

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

	protected transient List<Complex> values;

	protected transient List<Complex> quantizationValues;

	protected transient Set<ParameterType> applicableParameters;

	/**
	 * Calculated in SignalPropertiesCalculator
	 */
	protected transient Complex averageValue;
	protected transient Complex absoluteAverageValue;
	protected transient Complex signalPower;
	protected transient Complex signalVariance;
	protected transient Complex signalRootMeanSquareValue;

	/**
	 * Calculated in SignalValuesCalculator
	 */
	protected transient List<Complex> samples;

	public abstract Complex calculate(Complex sample) throws SignalParametersException;

	/**
	 * Checks if parameters are provided in order to avoid NullPointerException
	 * while calculating value for given sample.
	 * @return
	 */
	public abstract boolean areSampleCalculationParametersProvided();
}
