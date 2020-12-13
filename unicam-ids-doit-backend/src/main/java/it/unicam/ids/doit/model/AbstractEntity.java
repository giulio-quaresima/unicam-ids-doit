package it.unicam.ids.doit.model;

import java.util.Comparator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import it.unicam.ids.doit.utils.IdentityComparator;

@MappedSuperclass
public abstract class AbstractEntity<T extends AbstractEntity<?>> implements Comparable<T>
{
	public static final Comparator<AbstractEntity<?>> IDENTITY_COMPARATOR = new IdentityComparator<AbstractEntity<?>>();
	public static final Comparator<AbstractEntity<?>> ID_COMPARATOR = 
			Comparator.nullsLast(
					Comparator.comparing(AbstractEntity::getId, Comparator.nullsLast(Comparator.naturalOrder()))
					);
	public static final Comparator<AbstractEntity<?>> DEFAULT_COMPARATOR = ID_COMPARATOR.thenComparing(IDENTITY_COMPARATOR);
	
	private Integer id;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}
	
	protected abstract Class<T> entityType();

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
	public int compareTo(T o)
	{
		return DEFAULT_COMPARATOR.compare(this, o);
	}
	
}
