package it.unicam.ids.doit.dto;

import it.unicam.ids.doit.model.Appartenenza.Autorizzazione;
import it.unicam.ids.doit.model.json.JsonViews;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.model.Candidatura;
import it.unicam.ids.doit.model.Invito;
import it.unicam.ids.doit.model.Progetto;
import it.unicam.ids.doit.model.Soggetto;
import it.unicam.ids.doit.model.SoggettoCollettivo;
import it.unicam.ids.doit.model.SoggettoUtente;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
@JsonView(JsonViews.Candidatura.class)
public class CandidaturaDto extends AbstractDto<Candidatura>
{
	private boolean currentUserOwner = false;
	private boolean currentUserInvitato = false;
	private NavigableSet<SoggettoUtente> soggettiUtenteSuggeritiPerInvito = new TreeSet<>();
	private NavigableSet<SoggettoCollettivo> soggettiCollettiviSuggeritiPerInvito = new TreeSet<>();
	
	public CandidaturaDto(Candidatura adaptee)
	{
		super(adaptee);
	}
	
	/**
	 * @return <code>true</code> sse l'utente corrente è {@link Candidatura#getSoggetto()}
	 * o è membro di {@link Candidatura#getSoggetto()} con permesso {@link Autorizzazione#CANDIDATURA}. 
	 */
	public boolean isCurrentUserOwner()
	{
		return currentUserOwner;
	}
	public void setCurrentUserOwner(boolean currentUserOwner)
	{
		this.currentUserOwner = currentUserOwner;
	}

	/**
	 * @return <code>true</code> sse l'utente corrente è stato invitato
	 * ({@link Soggetto#getInvitiRicevuti()}) alla candidatura.
	 */
	public boolean isCurrentUserInvitato()
	{
		return currentUserInvitato;
	}
	public void setCurrentUserInvitato(boolean currentUserInvitato)
	{
		this.currentUserInvitato = currentUserInvitato;
	}
	
	/**
	 * Suggerisce un elenco di utenti che hanno almeno una delle
	 * competenze richieste dal progetto, escludendo ovviamente il current user,
	 * {@link Candidatura#getSoggetto()}, {@link Progetto#getProponente()} 
	 * e tutti i soggetti già invitati.
	 * 
	 * @return
	 */
	public NavigableSet<SoggettoUtente> getSoggettiUtenteSuggeritiPerInvito()
	{
		return soggettiUtenteSuggeritiPerInvito;
	}
	public void setSoggettiUtenteSuggeritiPerInvito(NavigableSet<SoggettoUtente> soggettiUtenteSuggeritiPerInvito)
	{
		this.soggettiUtenteSuggeritiPerInvito = soggettiUtenteSuggeritiPerInvito;
	}
	
	/**
	 * Suggerisce un elenco di soggetti collettivi che hanno almeno una delle
	 * competenze richieste dal progetto, escludendo ovviamente il current user,
	 * {@link Candidatura#getSoggetto()}, {@link Progetto#getProponente()} 
	 * e tutti i soggetti già invitati: le competenze di un soggetto
	 * collettivo si ricavano dalle competenze di tutti i progetti di cui
	 * è proponente.
	 * 
	 * @return
	 */
	public NavigableSet<SoggettoCollettivo> getSoggettiCollettiviSuggeritiPerInvito()
	{
		return soggettiCollettiviSuggeritiPerInvito;
	}
	public void setSoggettiCollettiviSuggeritiPerInvito(NavigableSet<SoggettoCollettivo> soggettiCollettiviSuggeritiPerInvito)
	{
		this.soggettiCollettiviSuggeritiPerInvito = soggettiCollettiviSuggeritiPerInvito;
	}
	

	// Delegate methods ///////////////////////////////////////////////////////////

	public boolean isInviata()
	{
		return adaptee.isInviata();
	}

	public boolean isSelezionata()
	{
		return adaptee.isSelezionata();
	}

	public String getIncarico()
	{
		return adaptee.getIncarico();
	}

	@JsonView(JsonViews.CandidaturaTree.class)
	public Soggetto<?> getSoggetto()
	{
		return adaptee.getSoggetto();
	}
	
	@JsonView(JsonViews.CandidaturaTree.class)
	public Set<Invito> getInviti()
	{
		return adaptee.getInviti();
	}

	@JsonView(JsonViews.CandidaturaTree.class)
	public Progetto getProgetto()
	{
		return adaptee.getProgetto();
	}
}
