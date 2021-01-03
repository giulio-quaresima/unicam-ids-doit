package it.unicam.ids.doit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
@Entity
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
	
	private Stato stato;
	private String titolo;
	private String descrizione;

	@Enumerated (EnumType.STRING)
	@Column (length = 32)
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

	@Override
	protected Class<Progetto> entityType()
	{
		return Progetto.class;
	}

}
