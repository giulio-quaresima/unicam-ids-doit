package it.unicam.ids.doit.model;

import javax.persistence.Entity;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
@Entity
public class Progetto extends AbstractEntity<Progetto>
{

	@Override
	protected Class<Progetto> getEntityType()
	{
		return Progetto.class;
	}

}
