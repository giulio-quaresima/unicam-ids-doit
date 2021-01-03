package it.unicam.ids.doit.model;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
@Entity
@Table (indexes = @Index (columnList = Appartenenza.SOGCOLL_COLUMN_NAME + "," + Appartenenza.UTENTE_COLUMN_NAME, unique = true))
public class Appartenenza extends AbstractEntity<Appartenenza>
{
	public enum Autorizzazione {
		GESTIONE_PROGETTO,
		CANDIDATURA
	}
	public static final String UTENTE_COLUMN_NAME = "membro";
	public static final String SOGCOLL_COLUMN_NAME = "organizzazione";
	
	private SoggettoUtente membro;
	private SoggettoCollettivo organizzazione;
	private Set<Autorizzazione> autorizzazioni;
	
	@ManyToOne
	@JoinColumn (name = UTENTE_COLUMN_NAME)
	public SoggettoUtente getMembro()
	{
		return membro;
	}
	public void setMembro(SoggettoUtente membro)
	{
		this.membro = membro;
	}
	
	@ManyToOne
	@JoinColumn (name = SOGCOLL_COLUMN_NAME)
	public SoggettoCollettivo getOrganizzazione()
	{
		return organizzazione;
	}
	public void setOrganizzazione(SoggettoCollettivo organizzazione)
	{
		this.organizzazione = organizzazione;
	}
	
	@Enumerated (EnumType.STRING)
	@ElementCollection
	public Set<Autorizzazione> getAutorizzazioni()
	{
		return autorizzazioni;
	}
	public void setAutorizzazioni(Set<Autorizzazione> autorizzazioni)
	{
		this.autorizzazioni = autorizzazioni;
	}
	
	@Override
	protected Class<Appartenenza> entityType()
	{
		return Appartenenza.class;
	}
	
}
