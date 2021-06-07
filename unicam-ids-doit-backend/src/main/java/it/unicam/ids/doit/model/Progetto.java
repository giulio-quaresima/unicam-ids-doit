package it.unicam.ids.doit.model;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SortNatural;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.model.json.JsonViews;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
@Entity
@Configurable
@JsonView(JsonViews.Progetto.class)
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
	@NotNull
	private Stato stato;
	
	@Basic
	@NotNull
	private String titolo;
	
	@Lob
	private String descrizione;
	
	@Lob
	private String obiettivi;
	
	@ManyToOne (optional = false)
	@NotNull
	private SoggettoCollettivo proponente;
	
	@OneToMany (mappedBy = "progetto")
	@JsonView(JsonViews.ProgettoTree.class)
	private Set<Candidatura> candidature = new HashSet<Candidatura>();

	@ManyToMany
	@JoinTable (name = "progetto_competenza", inverseJoinColumns = @JoinColumn (name = "id_competenza"), joinColumns = @JoinColumn (name = "id_progetto"))
	@SortNatural
	private SortedSet<Competenza> competenzas = new TreeSet<Competenza>();
	
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

	public SortedSet<Competenza> getCompetenzas()
	{
		return competenzas;
	}
	public void setCompetenzas(SortedSet<Competenza> competenzas)
	{
		this.competenzas = competenzas;
	}
	
	public SoggettoCollettivo getProponente()
	{
		return proponente;
	}
	public void setProponente(SoggettoCollettivo proponente)
	{
		this.proponente = proponente;
	}
	
	public Set<Candidatura> getCandidature()
	{
		return candidature;
	}
	public void setCandidature(Set<Candidatura> candidature)
	{
		this.candidature = candidature;
	}

	/**
	 * Per il significato vedi {@link #isVisibilePubblicamente()}.
	 */
	public static final EnumSet<Stato> STATI_VISIBILI_PUBBLICAMENTE = 
			EnumSet.of(Stato.PUBBLICATO, Stato.CANDIDATURE_IN_VALUTAZIONE, Stato.IN_REALIZZAZIONE, Stato.REALIZZATO);
	/**
	 * @return <code>true</code> sse questo progetto è visibile pubblicamente, cioè
	 * da qualsiasi utente, anche guest: in caso contrario, il progetto
	 * dovrà essere visibile solo dal suo {@link #getOwner()} e da altri soggetti
	 * eventualmente individuati dall'owner stesso.
	 */
	public boolean isVisibilePubblicamente()
	{
		return STATI_VISIBILI_PUBBLICAMENTE.contains(getStato());
	}

	@Override
	protected Class<Progetto> entityType()
	{
		return Progetto.class;
	}
	
	public void removeCompetenza(String tag)
	{
		if (StringUtils.hasText(tag))
		{
			Iterator<Competenza> iterator = getCompetenzas().iterator();
			while (iterator.hasNext())
			{
				Competenza competenza = iterator.next();
				if (tag.equals(competenza.getTag()))
				{
					iterator.remove();
				}
			}
		}
	}

}
