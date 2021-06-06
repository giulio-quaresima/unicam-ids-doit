package it.unicam.ids.doit.model;

import java.util.Comparator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import it.unicam.ids.doit.utils.IdentityComparator;

@MappedSuperclass
public abstract class AbstractEntity<E extends AbstractEntity<?>> implements Comparable<E>
{
	public static final Comparator<AbstractEntity<?>> IDENTITY_COMPARATOR = new IdentityComparator<AbstractEntity<?>>();
	public static final Comparator<AbstractEntity<?>> ID_COMPARATOR = 
			Comparator.nullsLast(
					Comparator.comparing(AbstractEntity::getId, Comparator.nullsLast(Comparator.naturalOrder()))
					);
	public static final Comparator<AbstractEntity<?>> DEFAULT_COMPARATOR = ID_COMPARATOR.thenComparing(IDENTITY_COMPARATOR);
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}
	
	/**
	 * @return La classe dell'entity: si raccomanda di non restituire
	 * il return value di {@link #getClass()}, perché tale metodo potrebbe
	 * restituire una classe di tipo Proxy o CGILIB o simili.
	 */
	protected abstract Class<E> entityType();
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		
		if (obj instanceof AbstractEntity<?>)
		{
			if (id != null)
			{
				AbstractEntity<?> other = (AbstractEntity<?>) obj;
				if (entityType().equals(other.entityType()))
				{
					return id.equals(other.id);
				}
			}
		}
		
		return false;
	}

	@Override
	public int compareTo(E o)
	{
		return DEFAULT_COMPARATOR.compare(this, o);
	}
	
}
