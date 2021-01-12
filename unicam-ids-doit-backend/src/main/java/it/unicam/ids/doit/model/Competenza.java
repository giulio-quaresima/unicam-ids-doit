package it.unicam.ids.doit.model;

import java.util.Comparator;

import javax.persistence.Entity;

import org.hibernate.annotations.NaturalId;

import it.unicam.ids.doit.utils.Comparators;

/**
 * Un tag che rappresenta una competenza: il natural order della classe
 * è {@link #NATURAL_ID_COMPARATOR}.
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--unipg.it, giulio.quaresima--at--gmail.com)
 */
@Entity
public class Competenza extends AbstractEntity<Competenza>
{
	/**
	 * Comparator singleton che stabilisce un ordinamento basato
	 * su {@link #getTag()}, basato su {@link Comparators#NULLS_LAST_STRING_COMPARATOR_CU}.
	 */
	public static final Comparator<Competenza> NATURAL_ID_COMPARATOR = Comparator.nullsLast(Comparator.comparing(Competenza::getTag, Comparators.NULLS_LAST_STRING_COMPARATOR_CU));
	
	private String tag;

	@NaturalId
	public String getTag()
	{
		return tag;
	}
	public void setTag(String tag)
	{
		this.tag = tag;
	}

	@Override
	protected Class<Competenza> entityType()
	{
		return Competenza.class;
	}
	
	@Override
	public int compareTo(Competenza other)
	{
		int compare = NATURAL_ID_COMPARATOR.compare(this, other);
		if (compare == 0)
		{
			compare = super.compareTo(other);
		}
		return compare;
	}

}
