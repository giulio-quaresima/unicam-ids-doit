package it.unicam.ids.doit.model;

import javax.persistence.Entity;

@Entity
public class Manager extends AbstractEntity<Manager>
{

	@Override
	protected Class<Manager> getEntityType()
	{
		return Manager.class;
	}

}
