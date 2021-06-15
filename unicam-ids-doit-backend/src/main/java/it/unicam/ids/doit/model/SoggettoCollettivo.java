package it.unicam.ids.doit.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.model.json.JsonViews;

@Entity
@DiscriminatorValue (value = "C")
@JsonView(JsonViews.SoggettoCollettivo.class)
public class SoggettoCollettivo extends Soggetto<SoggettoCollettivo>
{
	@Basic
	private String denominazione;
	
	@OneToMany (mappedBy = "proponente")
	@JsonView(JsonViews.SoggettoTree.class)
	private Set<Progetto> progettos;
	
	@OneToMany (mappedBy = "organizzazione")
	@JsonIgnore
	private Set<Appartenenza> membri = new HashSet<>();
	
	@Override
	public String getDenominazione()
	{
		return denominazione;
	}
	public void setDenominazione(String denominazione)
	{
		this.denominazione = denominazione;
	}

	public Set<Progetto> getProgettos()
	{
		return progettos;
	}
	public void setProgettos(Set<Progetto> progettos)
	{
		this.progettos = progettos;
	}
	
	public Set<Appartenenza> getMembri()
	{
		return membri;
	}
	public void setMembri(Set<Appartenenza> membri)
	{
		this.membri = membri;
	}
	
	@Override
	protected Class<SoggettoCollettivo> entityType()
	{
		return SoggettoCollettivo.class;
	}

}
