package manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import exception.SignalRepositoryException;
import model.request.SignalPropertiesCalculationRequest;
import model.signal.base.Signal;
import repository.SignalRepository;
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

	@Autowired
	private SignalRepository signalRepository;

	public Signal resolveSignalByType(String signalType) throws SignalParametersException
	{
		return signalTypeResolver.resolveSignalByType(signalType);
	}

	public List<Complex> getSignalSamples(Signal signal)
	{
		return signalSamplesCalculator.getSampleList(signal.getSamplingRate(), signal.getInitialTime(), signal.getEndTime());
	}

	public List<Complex> calculateSignalValues(Signal signal) throws SignalParametersException
	{
		List<Complex> samples = signal.getSamples();
		List<Complex> values = new ArrayList<>();

		for (int i = 0; i < samples.size(); i++)
		{
			values.add(signal.calculate(samples.get(i)));
		}

		return values;
	}

	public void extractDataFromSignalChartRequest(SignalPropertiesCalculationRequest request, Signal signal)
	{
		if (request.getInitialTime() != null)
		{
			double realInitialTime = request.getInitialTime().getReal();
			double imaginaryInitialTime = request.getInitialTime().getImaginary();
			signal.setInitialTime(new Complex(realInitialTime < 0 ? 0.0D : realInitialTime, imaginaryInitialTime < 0 ? 0.0D : imaginaryInitialTime));
		}

		if (request.getSamplingRate() != null)
		{
			double realSamplingRate = request.getSamplingRate().getReal();
			double imaginarySamplingRate = request.getSamplingRate().getImaginary();
			signal.setSamplingRate(new Complex(realSamplingRate <= 0 ? 1.0D : realSamplingRate, imaginarySamplingRate < 0 ? 1.0D : imaginarySamplingRate));
		}

		if (ObjectUtils.anyNotNull(request.getInitialTime(), request.getDuration()))
		{
			signal.setEndTime(request.getInitialTime().add(request.getDuration()));
		}

		signal.setAmplitude(request.getAmplitude());
		signal.setDuration(request.getDuration());
		signal.setPeriod(request.getPeriod());
		signal.setDutyCycle(signal.getDutyCycle());
	}

	public Complex calculateSignalAverageValue(Signal signal)
	{
		return signalPropertiesCalculator.calculateAaverageValue(signal);
	}

	public Complex calculateSignalAbsoluteAverageValue(Signal signal)
	{
		return signalPropertiesCalculator.calculateAbsolutetAverageValue(signal);
	}

	public Complex calculateSignalPower(Signal signal)
	{
		return signalPropertiesCalculator.calculateSignalPower(signal);
	}

	public Complex calculateSignalVariance(Signal signal)
	{
		return signalPropertiesCalculator.calculateVariance(signal, calculateSignalAverageValue(signal));
	}

	public Complex calculateSignalRootMeanSquareValue(Signal signal)
	{
		return signalPropertiesCalculator.calculateRootMeanSquareValue(signal);
	}

	public Signal findSignalInRepository(int signalId) throws SignalRepositoryException
	{
		return signalRepository.findOne(signalId);
	}
}
