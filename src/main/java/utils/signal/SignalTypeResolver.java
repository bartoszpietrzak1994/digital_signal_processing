package utils.signal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
	private ApplicationContext applicationContext;

	public Signal resolveSignalByType(String signalTypeName) throws SignalParametersException
	{
		SignalType signalType = SignalType.valueOf(signalTypeName);
		Signal signal = (Signal) applicationContext.getBean(signalType.name());

		if (signal == null)
		{
			throw SignalParametersException.signalTypeNotSupported(signalType.name());
		}

		return signal;
	}
}
