package it.unicam.ids.doit.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity<T extends AbstractEntity<?>>
{
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
	
	protected abstract Class<T> getEntityType();

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
				if (getEntityType().equals(other.getEntityType()))
				{
					return id.equals(other.id);
				}
			}
		}
		
		return false;
	}
	
}
