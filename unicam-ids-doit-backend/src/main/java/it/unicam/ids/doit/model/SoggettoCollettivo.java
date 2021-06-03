package it.unicam.ids.doit.model;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.model.json.JsonViews;

@Entity
@DiscriminatorValue (value = "C")
@JsonView(JsonViews.SoggettoCollettivo.class)
public class SoggettoCollettivo extends Soggetto<SoggettoCollettivo>
{
	@Basic
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
