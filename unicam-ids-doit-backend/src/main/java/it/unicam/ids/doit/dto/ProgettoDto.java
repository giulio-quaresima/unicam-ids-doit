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
	
	public ProgettoDto(Progetto delegate)
	{
		super(delegate);
	}
	
	public boolean isCurrentUserOwner()
	{
		return currentUserOwner;
	}
	public void setCurrentUserOwner(boolean currentUserOwner)
	{
		this.currentUserOwner = currentUserOwner;
	}
	
	public boolean isVisible()
	{
		return isVisibilePubblicamente() || isCurrentUserOwner();
	}

	public Stato getStato()
	{
		return delegate.getStato();
	}
	public String getTitolo()
	{
		return delegate.getTitolo();
	}
	public String getDescrizione()
	{
		return delegate.getDescrizione();
	}
	public String getObiettivi()
	{
		return delegate.getObiettivi();
	}
	public SortedSet<Competenza> getCompetenzas()
	{
		return delegate.getCompetenzas();
	}
	public SoggettoCollettivo getProponente()
	{
		return delegate.getProponente();
	}
	public Set<Candidatura> getCandidature()
	{
		return delegate.getCandidature();
	}
	public boolean isVisibilePubblicamente()
	{
		return delegate.isVisibilePubblicamente();
	}
}
