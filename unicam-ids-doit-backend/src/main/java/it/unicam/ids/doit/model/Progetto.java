package it.unicam.ids.doit.model;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import org.hibernate.annotations.SortNatural;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
@Entity
@Configurable
public class Progetto extends AbstractEntity<Progetto>
{
	public enum Stato {
		IN_MODIFICA,
		IN_VALUTAZIONE,
		PUBBLICATO,
		CANDIDATURE_IN_VALUTAZIONE,
		IN_REALIZZAZIONE,
		REALIZZATO,
		ANNULLATO,
		ELIMINATO
	}
	
	@Enumerated (EnumType.STRING)
	@Column (length = 32)
	private Stato stato;
	
	@Basic
	private String titolo;
	
	@Lob
	private String descrizione;
	
	@Lob
	private String obiettivi;
	
	@ManyToOne (optional = false)
	private SoggettoCollettivo owner;

	@ManyToMany
	@JoinTable (name = "progetto_competenza")
	@SortNatural
	private SortedSet<Competenza> competenze = new TreeSet<Competenza>();
	
	@Transient
	private List<String> competenzeTags = new ArrayList<String>();
	
	@PostLoad
	public void postLoad()
	{
		this.competenzeTags = competenze.stream().map(Competenza::getTag).collect(Collectors.toList());
	}

	public Stato getStato()
	{
		return stato;
	}
	public void setStato(Stato stato)
	{
		this.stato = stato;
	}
	
	public String getTitolo()
	{
		return titolo;
	}
	public void setTitolo(String titolo)
	{
		this.titolo = titolo;
	}

	public String getDescrizione()
	{
		return descrizione;
	}
	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}

	public String getObiettivi()
	{
		return obiettivi;
	}
	public void setObiettivi(String obiettivi)
	{
		this.obiettivi = obiettivi;
	}

	public SortedSet<Competenza> getCompetenze()
	{
		return competenze;
	}
	public void setCompetenze(SortedSet<Competenza> competenze)
	{
		this.competenze = competenze;
	}
	
	public List<String> getCompetenzeTags()
	{
		return this.competenzeTags;
	}
	public void setCompetenzeTags(List<String> competenzeTags)
	{
		this.competenzeTags = competenzeTags;
	}
	
	public SoggettoCollettivo getOwner()
	{
		return owner;
	}
	public void setOwner(SoggettoCollettivo owner)
	{
		this.owner = owner;
	}

	@Override
	protected Class<Progetto> entityType()
	{
		return Progetto.class;
	}

}
