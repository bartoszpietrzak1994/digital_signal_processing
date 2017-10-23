package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import exception.SignalIOException;
import manager.SignalManager;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
@Component
public class SignalService
{
	@Autowired
	private SignalManager signalManager;

	public boolean saveSignal(Signal signal) throws SignalIOException
	{
		if (CollectionUtils.isEmpty(signal.getValues()))
		{
			throw SignalIOException.signalValuesNotAvailable();
		}

		return false;
	}


}
