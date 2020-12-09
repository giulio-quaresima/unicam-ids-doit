package it.unicam.ids.doit.model;

import javax.persistence.Entity;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--unipg.it, giulio.quaresima--at--gmail.com)
 */
@Entity
public class SoggettoCollettivo extends AbstractEntity<SoggettoCollettivo>
{
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

}
