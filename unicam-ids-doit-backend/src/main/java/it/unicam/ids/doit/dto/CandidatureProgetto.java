package it.unicam.ids.doit.dto;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import it.unicam.ids.doit.model.Candidatura;
import it.unicam.ids.doit.model.Progetto;

/**
 * DTO che rappresenta un progetto e le candidature
 * che in un dato contesto pertengono a tale progetto.
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
public class CandidatureProgetto
{
	private final Progetto progetto;
	private final Set<Candidatura> candidature;
	
	/**
	 * Crea questo oggetto con il progetto passato, me mettendo
	 * in {@link #getCandidature()} tutte le {@code candidature}
	 * riferite al progetto in questione
	 * 
	 * @param progetto
	 * @param candidature
	 */
	public CandidatureProgetto(Progetto progetto, Collection<Candidatura> candidature)
	{
		super();
		this.progetto = Objects.requireNonNull(progetto);
		this.candidature = candidature
				.stream()
				.filter(c -> c.getProgetto().equals(progetto))
				.collect(Collectors.toSet())
				;
	}

	public Progetto getProgetto()
	{
		return progetto;
	}

	public Set<Candidatura> getCandidature()
	{
		return candidature;
	}
	
}
