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
	AMPLITUDE_RISE_COUNTERPART,

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
	FIRST_COUNTERPART,
	DISCRETIZATION_FREQUENCY,

	/**
	 * STATISTICS - related
	 */
	AVERAGE_VALUE,
	VALUE_PRESENCE_PROBABILITY
}
