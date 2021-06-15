package it.unicam.ids.doit.model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import it.unicam.ids.doit.model.json.JsonViews;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--unipg.it, giulio.quaresima--at--gmail.com)
 */
@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn (name = "discriminator_column", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue (value = "X")
@JsonTypeInfo (use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes ({
	@JsonSubTypes.Type (name = "utente", value = SoggettoUtente.class),
	@JsonSubTypes.Type (name = "collettivo", value = SoggettoCollettivo.class)})
@JsonView(JsonViews.Soggetto.class)
public abstract class Soggetto<E extends Soggetto<?>> extends AbstractEntity<E> implements Comparable<E>
{
	public static final Comparator<Soggetto<?>> DENOMINAZIONE_COMPARATOR = 
			Comparator.nullsLast(
					Comparator.comparing(
							Soggetto::getDenominazione, 
							Comparator.nullsLast(Comparator.naturalOrder())
							)
					);
	public static final Comparator<Soggetto<?>> DEFAULT_COMPARATOR = 
			DENOMINAZIONE_COMPARATOR.thenComparing(AbstractEntity.DEFAULT_COMPARATOR);
	
	@OneToMany (mappedBy = "soggetto")
	@JsonView (JsonViews.SoggettoTree.class)
	private Set<Candidatura> candidature = new HashSet<Candidatura>();
	
	@OneToMany (mappedBy = "invitato")
	@JsonView (JsonViews.SoggettoTree.class)
	private Set<Invito> invitiRicevuti = new HashSet<>();

	/**
	 * Le candidature di cui questo soggetto è capofila (owner).
	 * 
	 * @return
	 */
	public Set<Candidatura> getCandidature()
	{
		return candidature;
	}
	public void setCandidature(Set<Candidatura> candidature)
	{
		this.candidature = candidature;
	}

	/**
	 * Gli inviti ricevuti a partecipare ad una candidatura.
	 * 
	 * @return
	 */
	public Set<Invito> getInvitiRicevuti()
	{
		return invitiRicevuti;
	}
	public void setInvitiRicevuti(Set<Invito> invitiRicevuti)
	{
		this.invitiRicevuti = invitiRicevuti;
	}
	
	@JsonView (JsonViews.SoggettoTree.class)
	public Set<Invito> getInvitiRicevutiNonAncoraAccettati()
	{
		return getInvitiRicevuti().stream().filter(Invito::isAccettazioneNull).collect(Collectors.toSet());
	}
	
	/**
	 * Facility per ottenere direttamente le candidature
	 * passando per {@link #getInvitiRicevuti()}.
	 * 
	 * @return
	 */
	@JsonView (JsonViews.SoggettoTree.class)
	public Set<Candidatura> getCandidatureDaInvitoRicevuto()
	{
		return getInvitiRicevuti().stream().map(Invito::getCandidatura).collect(Collectors.toSet());
	}
	
	public abstract String getDenominazione();

	@Override
	public int compareTo(E o)
	{
		return DEFAULT_COMPARATOR.compare(this, o);
	}

}
