package it.unicam.ids.doit.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.unicam.ids.doit.model.AbstractEntity;

public abstract class AbstractDto<E extends AbstractEntity<E>>
{
	protected final E delegate;

	public AbstractDto(E delegate)
	{
		super();
		this.delegate = Objects.requireNonNull(delegate);
	}

	@JsonIgnore // La visibilità dev'essere garantita dai metodi delegate, non attraverso il delegate stesso
	public E getDelegate()
	{
		return delegate;
	}

	public Integer getId()
	{
		return delegate.getId();
	}

	public int hashCode()
	{
		return delegate.hashCode();
	}

	public boolean equals(Object obj)
	{
		return delegate.equals(obj);
	}

	public int compareTo(E o)
	{
		return delegate.compareTo(o);
	}

	public String toString()
	{
		return delegate.toString();
	}
	
}
