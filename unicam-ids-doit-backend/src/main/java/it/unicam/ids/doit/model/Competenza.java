package it.unicam.ids.doit.model;

import javax.persistence.Entity;

import org.hibernate.annotations.NaturalId;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--unipg.it, giulio.quaresima--at--gmail.com)
 */
@Entity
public class Competenza extends AbstractEntity<Competenza>
{
	private String tag;

	@NaturalId
	public String getTag()
	{
		return tag;
	}
	public void setTag(String tag)
	{
		this.tag = tag;
	}

	@Override
	protected Class<Competenza> entityType()
	{
		return Competenza.class;
	}

}
