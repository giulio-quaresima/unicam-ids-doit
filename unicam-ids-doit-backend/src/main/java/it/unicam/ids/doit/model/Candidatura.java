package it.unicam.ids.doit.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Candidatura extends AbstractEntity<Candidatura>
{
	@Basic
	private String autopromozione;

	@Basic
	private boolean inviata = false;
	
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
