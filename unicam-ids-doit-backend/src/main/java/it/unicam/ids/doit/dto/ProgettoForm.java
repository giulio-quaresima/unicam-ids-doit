package it.unicam.ids.doit.dto;

import it.unicam.ids.doit.model.Progetto;
import it.unicam.ids.doit.model.SoggettoCollettivo;

public class ProgettoForm
{
	private Progetto progetto;
	private SoggettoCollettivo owner;
	
	public Progetto getProgetto()
	{
		return progetto;
	}
	public void setProgetto(Progetto progetto)
	{
		this.progetto = progetto;
	}
	public SoggettoCollettivo getOwner()
	{
		return owner;
	}
	public void setOwner(SoggettoCollettivo owner)
	{
		this.owner = owner;
	}

}
