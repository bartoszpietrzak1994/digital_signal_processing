package model.signal;

/**
 * Created by bartoszpietrzak on 06/10/2017.
 */
public enum SignalType
{
	/**
	 * NOISE
	 */
	DISCRETE_UNIFORM_DISTRIBUTION_NOISE,
	GAUSS_NOISE,
	IMPULSE_NOISE,

	/**
	 * SIGNAL
	 */
	SINE_SIGNAL,
	SINE_SIGNAL_HALF_RECTIFIED,
	SINE_SIGNAL_FULLY_RECTIFIED,
	RECTANGULAR_SIGNAL,
	RECTANGULAR_SYMMETRIC_SIGNAL,
	TRIANGLE_SIGNAL,

	/**
	 * STEP FUNCTION
	 */
	STEP_FUNCTION,

	/**
	 * IMPULSE
	 */
	UNIT_IMPULSE,

	/**
	 * RESULT OF SIGNAL OPERATIONS
	 */
	UNKNOWN
}
