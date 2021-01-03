package it.unicam.ids.doit.model;

import java.util.Comparator;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

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
	
	@Transient
	public abstract String getDenominazione();

	@Override
	public int compareTo(E o)
	{
		return DEFAULT_COMPARATOR.compare(this, o);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Class<E> entityType()
	{
		return (Class<E>) Soggetto.class;
	}

}
