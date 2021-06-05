package it.unicam.ids.doit.dto;

import java.util.function.Function;
import java.util.stream.Stream;

import it.unicam.ids.doit.model.AbstractEntity;

public abstract class AbstractDtoFactory<E extends AbstractEntity<E>, T extends AbstractDto<E>>
{
	public T adapt(E entity)
	{
		return adapter().apply(entity);
	}
	
	public Stream<T> adaptAll(Stream<E> entities)
	{
		return entities.map(adapter());
	}	
	
	public abstract Function<E,T> adapter();
}
