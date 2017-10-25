package utils.signal;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import model.signal.SignalType;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 21/10/2017.
 */
@Component
public class SignalTypeResolver
{
	@Autowired
	private List<Signal> signals;

	public Signal resolveSignalByType(String signalTypeName) throws SignalParametersException
	{
		SignalType signalType = SignalType.valueOf(signalTypeName);

		Optional<Signal> optionalSignal = signals.stream().filter(signal -> signal.getSignalType().equals(signalType)).findFirst();

		if (!optionalSignal.isPresent())
		{
			throw SignalParametersException.signalTypeNotSupported(signalType.name());
		}

		return optionalSignal.get();
	}
}
