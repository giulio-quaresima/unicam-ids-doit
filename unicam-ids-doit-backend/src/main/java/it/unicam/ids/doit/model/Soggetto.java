package it.unicam.ids.doit.model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--unipg.it, giulio.quaresima--at--gmail.com)
 */
@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn (name = "discriminator_column", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue (value = "X")
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
	private Set<Candidatura> candidature = new HashSet<Candidatura>();
	
	public Set<Candidatura> getCandidature()
	{
		return candidature;
	}
	public void setCandidature(Set<Candidatura> candidature)
	{
		this.candidature = candidature;
	}

	public abstract String getDenominazione();

	@Override
	public int compareTo(E o)
	{
		return DEFAULT_COMPARATOR.compare(this, o);
	}

}
