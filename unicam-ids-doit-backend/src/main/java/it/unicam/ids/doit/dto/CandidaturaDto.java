package it.unicam.ids.doit.dto;

import it.unicam.ids.doit.model.Appartenenza.Autorizzazione;
import it.unicam.ids.doit.model.json.JsonViews;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.model.Candidatura;
import it.unicam.ids.doit.model.Invito;
import it.unicam.ids.doit.model.Progetto;
import it.unicam.ids.doit.model.Soggetto;

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
