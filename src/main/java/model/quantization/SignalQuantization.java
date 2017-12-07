package model.quantization;

import org.apache.commons.math.complex.Complex;

import lombok.Getter;
import model.behaviour.QuantizationType;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 07/12/2017.
 */
public abstract class SignalQuantization
{
	@Getter
	protected QuantizationType quantizationType;

	public abstract Signal signalQuantization(Signal signal, Complex quantLevel);
}
