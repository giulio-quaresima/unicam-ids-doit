package it.unicam.ids.doit.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Candidatura extends AbstractEntity<Candidatura>
{
	/**
	 * Campo descrittivo con il quale il Progettista, 
	 * unitamente ad eventuali documenti allegati (Documento), 
	 * promuove la propria candidatura al Progetto, 
	 * cercando di convincere il Proponente di avere l'esperienza, 
	 * le competenze e le capacità per farlo. 
	 * Può essere implementato come override dell'attributo descrizione : string di Descrizione
	 */
	@Basic
	private String autopromozione;

	/**
	 * La candidatura può avere due stati, in bozza (false) e inviata (true).
	 */
	@Basic
	private boolean inviata = false;
	
	/**
	 * Se true, significa che questa candidatura, 
	 * comprensiva del capofila e degli eventuali 
	 * altri progettisti invitati dal capofila che abbiano accettata, 
	 * è stata selezionata dal proponente per implementare il progetto.
	 */
	@Basic
	private boolean selezionata = false;
	
	/**
	 * La descrizione dell'incarico assegnato al Progettista 
	 * come parte della cordata individuata dal Proponente 
	 * nella realizzazione del Progetto.
	 */
	@Basic
	private String incarico;
	
	@ManyToOne
	private Soggetto<?> soggetto;
	
	@ManyToOne
	private Progetto progetto;

	public String getAutopromozione()
	{
		return autopromozione;
	}
	public void setAutopromozione(String autopromozione)
	{
		this.autopromozione = autopromozione;
	}

	public boolean isInviata()
	{
		return inviata;
	}
	public void setInviata(boolean inviata)
	{
		this.inviata = inviata;
	}

	public boolean isSelezionata()
	{
		return selezionata;
	}
	public void setSelezionata(boolean selezionata)
	{
		this.selezionata = selezionata;
	}
	
	public String getIncarico()
	{
		return incarico;
	}
	public void setIncarico(String incarico)
	{
		this.incarico = incarico;
	}
	
	public Soggetto<?> getSoggetto()
	{
		return soggetto;
	}
	public void setSoggetto(Soggetto<?> soggetto)
	{
		this.soggetto = soggetto;
	}

	public Progetto getProgetto()
	{
		return progetto;
	}
	public void setProgetto(Progetto progetto)
	{
		this.progetto = progetto;
	}

	@Override
	protected Class<Candidatura> entityType()
	{
		return Candidatura.class;
	}

}
