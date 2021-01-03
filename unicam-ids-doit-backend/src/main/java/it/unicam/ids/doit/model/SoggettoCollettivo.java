package it.unicam.ids.doit.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue (value = "C")
public class SoggettoCollettivo extends Soggetto<SoggettoCollettivo>
{
	private String denominazione;
	
	@Override
	public String getDenominazione()
	{
		return denominazione;
	}
	public void setDenominazione(String denominazione)
	{
		this.denominazione = denominazione;
	}

	@Override
	protected Class<SoggettoCollettivo> entityType()
	{
		return SoggettoCollettivo.class;
	}

}
