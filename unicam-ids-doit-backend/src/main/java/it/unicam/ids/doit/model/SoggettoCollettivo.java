package it.unicam.ids.doit.model;

import java.util.Comparator;

import javax.persistence.Entity;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--unipg.it, giulio.quaresima--at--gmail.com)
 */
@Entity
public class SoggettoCollettivo extends AbstractEntity<SoggettoCollettivo> implements Comparable<SoggettoCollettivo>
{
	public static final Comparator<SoggettoCollettivo> NOME_COMPARATOR = 
			Comparator.nullsLast(
					Comparator.comparing(
							SoggettoCollettivo::getNome, 
							Comparator.nullsLast(Comparator.naturalOrder())
							)
					);
	public static final Comparator<SoggettoCollettivo> DEFAULT_COMPARATOR = 
			NOME_COMPARATOR.thenComparing(AbstractEntity.DEFAULT_COMPARATOR);
	
	private String nome;
	private String descrizione;

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getDescrizione()
	{
		return descrizione;
	}

	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}

	@Override
	protected Class<SoggettoCollettivo> entityType()
	{
		return SoggettoCollettivo.class;
	}

	@Override
	public int compareTo(SoggettoCollettivo o)
	{
		return DEFAULT_COMPARATOR.compare(this, o);
	}

}
