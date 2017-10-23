package manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.signal.base.Signal;
import utils.signal.SignalTypeResolver;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
@Component
public class SignalManager
{
	@Autowired
	private SignalTypeResolver signalTypeResolver;

	private Signal resolveSignalByType(String signalType)
	{
		return signalTypeResolver.resolveSignalByType(signalType);
	}
}
