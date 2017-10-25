package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	public int add(Signal signal)
	{
		int id;
		Signal last = Iterables.getLast(signals, null);

		if (last == null)
		{
			id = 1;
		}
		else
		{
			id = last.getId() + 1;
		}

		signal.setId(id);

		signals.add(signal);
		return id;
	}

	public boolean delete(int id) throws SignalRepositoryException
	{
		Optional<Signal> first = signals.stream().filter(signal -> signal.getId() == id).findFirst();

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

	public Signal findOne(int id) throws SignalRepositoryException
	{
		Optional<Signal> first = signals.stream().filter(signal -> signal.getId() == id).findFirst();

		if (!first.isPresent())
		{
			throw SignalRepositoryException.signalNotPresentInRepository(id);
		}

		return first.get();
	}
}
