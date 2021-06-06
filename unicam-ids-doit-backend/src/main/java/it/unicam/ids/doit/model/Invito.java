package it.unicam.ids.doit.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.model.json.JsonViews;

@Entity
@JsonView (JsonViews.Invito.class)
public class Invito extends AbstractEntity<Invito>
{
	@ManyToOne (optional = false)
	@NotNull
	@JsonView ({JsonViews.SoggettoTree.class})
	private Candidatura candidatura;
	
	@ManyToOne (optional = false)
	@NotNull
	@JsonView ({JsonViews.CandidaturaTree.class})
	private Soggetto<?> invitato;
	
	@Basic
	private boolean accettazione;
	
	@Basic
	private String noteInvito;
	
	@Basic
	private String noteAccettazione;

	/**
	 * La candidatura alla quale si è invitati
	 * a partecipare.
	 * 
	 * @return
	 */
	public Candidatura getCandidatura()
	{
		return candidatura;
	}

	public void setCandidatura(Candidatura candidatura)
	{
		this.candidatura = candidatura;
	}

	/**
	 * Il soggetto invitato a partecipare alla
	 * candidatura.
	 * 
	 * @return
	 */
	public Soggetto<?> getInvitato()
	{
		return invitato;
	}

	public void setInvitato(Soggetto<?> invitato)
	{
		this.invitato = invitato;
	}

	/**
	 * <code>true</code> sse {@link #getInvitato()}
	 * ha accettato l'invito.
	 * 
	 * @return
	 */
	public boolean isAccettazione()
	{
		return accettazione;
	}

	public void setAccettazione(boolean accettazione)
	{
		this.accettazione = accettazione;
	}

	/**
	 * Note in cui si descrive il motivo dell'invito a partecipare.
	 * 
	 * @return
	 */
	public String getNoteInvito()
	{
		return noteInvito;
	}

	public void setNoteInvito(String noteInvito)
	{
		this.noteInvito = noteInvito;
	}

	/**
	 * Eventuali note in cui si motiva l'accettazione (o il diniego)
	 * all'invito.
	 * 
	 * @return
	 */
	public String getNoteAccettazione()
	{
		return noteAccettazione;
	}

	public void setNoteAccettazione(String noteAccettazione)
	{
		this.noteAccettazione = noteAccettazione;
	}

	@Override
	protected Class<Invito> entityType()
	{
		return Invito.class;
	}

}
