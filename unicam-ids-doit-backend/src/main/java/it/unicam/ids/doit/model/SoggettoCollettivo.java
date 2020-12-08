package it.unicam.ids.doit.model;

import javax.persistence.Entity;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--unipg.it, giulio.quaresima--at--gmail.com)
 */
@Entity
public class SoggettoCollettivo extends AbstractEntity<SoggettoCollettivo>
{

	@Override
	protected Class<SoggettoCollettivo> getEntityType()
	{
		return SoggettoCollettivo.class;
	}

}
