package it.unicam.ids.doit.model;

import javax.persistence.Entity;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--unipg.it, giulio.quaresima--at--gmail.com)
 */
@Entity
public class Proposta extends AbstractEntity<Proposta>
{

	@Override
	protected Class<Proposta> getEntityType()
	{
		return Proposta.class;
	}

}
