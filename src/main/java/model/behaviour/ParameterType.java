package model.behaviour;

/**
 * Created by bartoszpietrzak on 16/10/2017.
 */
public enum ParameterType
{
	/**
	 * AMPLITUDE - related
	 */
	AMPLITUDE,
	AMPLITUDE_RISE_TIME,
	AMPLITUDE_RISE_SAMPLE,

	/**
	 * TIME - related
	 */
	INITIAL_TIME,
	PERIOD,
	DURATION,
	DUTY_CYCLE,

	/**
	 * DISCRETIZATION - related
	 */
	SAMPLING_RATE,

	/**
	 * STATISTICS - related
	 */
	VALUE_PRESENCE_PROBABILITY
}
