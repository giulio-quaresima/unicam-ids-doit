package it.unicam.ids.doit.dto;

import java.util.Set;
import java.util.SortedSet;

import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.model.Candidatura;
import it.unicam.ids.doit.model.Competenza;
import it.unicam.ids.doit.model.Progetto;
import it.unicam.ids.doit.model.Progetto.Stato;
import it.unicam.ids.doit.model.SoggettoCollettivo;
import it.unicam.ids.doit.model.json.JsonViews;

@JsonView(JsonViews.Progetto.class)
public class ProgettoDto extends AbstractDto<Progetto>
{
	private boolean currentUserOwner = false;
	private boolean currentUserPuoCandidarsi = false;
	
	public ProgettoDto(Progetto adaptee)
	{
		super(adaptee);
	}
	
	public boolean isCurrentUserOwner()
	{
		return currentUserOwner;
	}
	public void setCurrentUserOwner(boolean currentUserOwner)
	{
		this.currentUserOwner = currentUserOwner;
	}
	
	public boolean isCurrentUserPuoCandidarsi()
	{
		return currentUserPuoCandidarsi;
	}
	public void setCurrentUserPuoCandidarsi(boolean currentUserPuoCandidarsi)
	{
		this.currentUserPuoCandidarsi = currentUserPuoCandidarsi;
	}

	public boolean isVisible()
	{
		return isVisibilePubblicamente() || isCurrentUserOwner();
	}
	
	
	// Delegate methods ///////////////////////////////////////////////////////////

	public Stato getStato()
	{
		return adaptee.getStato();
	}
	public String getTitolo()
	{
		return adaptee.getTitolo();
	}
	public String getDescrizione()
	{
		return adaptee.getDescrizione();
	}
	public String getObiettivi()
	{
		return adaptee.getObiettivi();
	}
	public SortedSet<Competenza> getCompetenzas()
	{
		return adaptee.getCompetenzas();
	}
	public SoggettoCollettivo getProponente()
	{
		return adaptee.getProponente();
	}
	@JsonView(JsonViews.ProgettoTree.class)
	public Set<Candidatura> getCandidature()
	{
		return adaptee.getCandidature();
	}
	public boolean isVisibilePubblicamente()
	{
		return adaptee.isVisibilePubblicamente();
	}
}
