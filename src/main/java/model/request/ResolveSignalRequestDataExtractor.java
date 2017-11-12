package model.request;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 12/11/2017.
 */
@Component
public class ResolveSignalRequestDataExtractor
{
	public void extractDataFromSignalChartRequest(ResolveSignalRequest request, Signal signal) throws SignalParametersException
	{
		if (!signal.areSampleCalculationParametersProvided())
		{
			throw SignalParametersException.calculationDataNotProvided(signal.getApplicableParameters());
		}

		// Initial time
		double realInitialTime = request.getInitialTime().getReal();
		double imaginaryInitialTime = request.getInitialTime().getImaginary();
		signal.setInitialTime(new Complex(realInitialTime < 0 ? 0.0D : realInitialTime, imaginaryInitialTime < 0 ? 0.0D : imaginaryInitialTime));

		// Sampling rate
		double realSamplingRate = request.getSamplingRate().getReal();
		double imaginarySamplingRate = request.getSamplingRate().getImaginary();
		signal.setSamplingRate(new Complex(realSamplingRate <= 0 ? 1.0D : realSamplingRate, imaginarySamplingRate < 0 ? 1.0D : imaginarySamplingRate));

		// End time
		signal.setEndTime(request.getInitialTime().add(request.getDuration()));

		signal.setAmplitude(request.getAmplitude());
		signal.setDuration(request.getDuration());
		signal.setPeriod(request.getPeriod());
		signal.setDutyCycle(signal.getDutyCycle());
	}
}
