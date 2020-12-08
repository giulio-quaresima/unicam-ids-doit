package it.unicam.ids.doit.model;

import javax.persistence.Entity;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--unipg.it, giulio.quaresima--at--gmail.com)
 */
@Entity
public class Competenza extends AbstractEntity<Competenza>
{

	@Override
	protected Class<Competenza> getEntityType()
	{
		return Competenza.class;
	}

}
