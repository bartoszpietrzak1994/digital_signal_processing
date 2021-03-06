package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import com.google.common.collect.Iterables;

import exception.SignalRepositoryException;
import model.signal.base.Signal;

/**
 * Created by bartoszpietrzak on 25/10/2017.
 */
@Component
public class SignalRepository
{
	private List<Signal> signals = new ArrayList<>();

	public String add(Signal signal)
	{
		Signal last = Iterables.getLast(signals, null);
		String signalId = last == null ? String.valueOf(1) : String.valueOf(Integer.valueOf(last.getId()) + 1);
		signal.setId(signalId);

		signals.add(signal);
		return signalId;
	}

	public boolean delete(String id) throws SignalRepositoryException
	{
		Optional<Signal> first = signals.stream().filter(signal -> signal.getId().equals(id)).findFirst();

		if (!first.isPresent())
		{
			throw SignalRepositoryException.signalNotPresentInRepository(id);
		}

		signals.remove(first.get());

		return true;
	}

	public List<Signal> find()
	{
		return signals;
	}

	public Signal findOne(String id) throws SignalRepositoryException
	{
		Optional<Signal> first = signals.stream().filter(signal -> signal.getId().equals(id)).findFirst();

		if (!first.isPresent())
		{
			throw SignalRepositoryException.signalNotPresentInRepository(id);
		}

		return first.get();
	}

	public String update(Signal signal)
	{
		Optional<Signal> optionalSignal = signals.stream().filter(storedSignal -> signal.getId().equals(storedSignal
				.getId())).findFirst();

		if (!optionalSignal.isPresent())
		{
			return add(signal);
		}

		Signal storedSignal = optionalSignal.get();

		signals.set(signals.indexOf(storedSignal), signal);

		return storedSignal.getId();
	}
}
