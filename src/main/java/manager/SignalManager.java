package manager;

import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.signal.base.Signal;
import utils.calculator.SignalPropertiesCalculator;
import utils.calculator.SignalSamplesCalculator;
import utils.signal.SignalTypeResolver;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
@Component
public class SignalManager
{
	@Autowired
	private SignalTypeResolver signalTypeResolver;

	@Autowired
	private SignalSamplesCalculator signalSamplesCalculator;

	@Autowired
	private SignalPropertiesCalculator signalPropertiesCalculator;

	public Signal resolveSignalByType(String signalType)
	{
		return signalTypeResolver.resolveSignalByType(signalType);
	}

	public List<Complex> getSignalSamples(Complex samplingRate, Complex initialTime, Complex endTime)
	{
		return signalSamplesCalculator.getSampleList(samplingRate, initialTime, endTime);
	}
}
