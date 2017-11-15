package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
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
	// TODO
	private AtomicLong id = new AtomicLong();

	private List<Signal> signals = new ArrayList<>();

	public int add(Signal signal)
	{
		id.incrementAndGet();
		signal.setId(id.intValue());

		signals.add(signal);
		return id.intValue();
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
