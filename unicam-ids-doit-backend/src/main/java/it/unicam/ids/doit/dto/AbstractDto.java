package it.unicam.ids.doit.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.unicam.ids.doit.model.AbstractEntity;

public abstract class AbstractDto<E extends AbstractEntity<E>>
{
	protected final E adaptee;

	public AbstractDto(E adaptee)
	{
		super();
		this.adaptee = Objects.requireNonNull(adaptee);
	}

	@JsonIgnore // La visibilità dev'essere garantita dai metodi delegate, non attraverso il delegate stesso
	public E getDelegate()
	{
		return adaptee;
	}

	public Integer getId()
	{
		return adaptee.getId();
	}

	public int hashCode()
	{
		return adaptee.hashCode();
	}

	public boolean equals(Object obj)
	{
		return adaptee.equals(obj);
	}

	public int compareTo(E o)
	{
		return adaptee.compareTo(o);
	}

	public String toString()
	{
		return adaptee.toString();
	}
	
}
