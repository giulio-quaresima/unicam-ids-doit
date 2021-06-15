package it.unicam.ids.doit.dto;

import java.util.Collections;
import java.util.NavigableSet;
import java.util.Set;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.ids.doit.model.Appartenenza;
import it.unicam.ids.doit.model.Appartenenza.Autorizzazione;
import it.unicam.ids.doit.model.Candidatura;
import it.unicam.ids.doit.model.Soggetto;
import it.unicam.ids.doit.model.SoggettoCollettivo;
import it.unicam.ids.doit.model.SoggettoUtente;
import it.unicam.ids.doit.repo.SoggettoCollettivoRepository;
import it.unicam.ids.doit.repo.SoggettoUtenteRepository;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
@Service
public class CandidaturaDtoFactory extends AbstractDtoFactory<Candidatura, CandidaturaDto>
{
	@Autowired
	private SoggettoUtenteRepository soggettoUtenteRepository;
	
	@Autowired
	private SoggettoCollettivoRepository soggettoCollettivoRepository;
	
	@Override
	public Function<Candidatura, CandidaturaDto> adapter()
	{
		Set<Candidatura> candidatureOwned;
		Set<Candidatura> candidatureAlleQualiSonoStatoInvitato;
		SoggettoUtente utenteAutenticato = findCurrentUser();
		if (utenteAutenticato != null)
		{
			candidatureOwned = utenteAutenticato.getCandidatureAll();
			candidatureAlleQualiSonoStatoInvitato = utenteAutenticato.getCandidatureDaInvitoRicevuto();
		}
		else
		{
			candidatureOwned = candidatureAlleQualiSonoStatoInvitato = Collections.emptySet();
		}
		return candidatura -> {
			CandidaturaDto candidaturaDto = new CandidaturaDto(candidatura);
			candidaturaDto.setCurrentUserOwner(candidatureOwned.contains(candidatura));
			candidaturaDto.setCurrentUserInvitato(candidatureAlleQualiSonoStatoInvitato.contains(candidatura));
			candidaturaDto.setSoggettiUtenteSuggeritiPerInvito(getSoggettiUtenteSuggeritiPerInvito(candidatura, utenteAutenticato));
			candidaturaDto.setSoggettiCollettiviSuggeritiPerInvito(getSoggettiCollettiviSuggeritiPerInvito(candidatura, utenteAutenticato));
			return candidaturaDto;
		};
	}

	private NavigableSet<SoggettoCollettivo> getSoggettiCollettiviSuggeritiPerInvito(Candidatura candidatura, SoggettoUtente utenteAutenticato)
	{
		NavigableSet<SoggettoCollettivo> navigableSet = soggettoCollettivoRepository.findByProgettosCompetenzasIn(candidatura.getProgetto().getCompetenzas());
		navigableSet = rimuoviSoggettiImpropri(navigableSet, candidatura, utenteAutenticato);
		return navigableSet;
	}

	private <S extends Soggetto<S>> NavigableSet<S> rimuoviSoggettiImpropri(NavigableSet<S> navigableSet, Candidatura candidatura, SoggettoUtente utenteAutenticato)
	{
		if (utenteAutenticato != null)
		{			
			navigableSet.remove(utenteAutenticato);
		}
		navigableSet.removeAll(candidatura.getProgetto().getCandidati());
		navigableSet.removeAll(candidatura.getInvitati());
		SoggettoCollettivo proponente = candidatura.getProgetto().getProponente();
		if (proponente != null)
		{			
			proponente
				.getMembri()
				.stream()
				.filter(appartenenza -> appartenenza.getAutorizzazioni().contains(Autorizzazione.GESTIONE_PROGETTO))
				.map(Appartenenza::getMembro)
				.forEach(navigableSet::remove);
		}
		return navigableSet;
	}

	private NavigableSet<SoggettoUtente> getSoggettiUtenteSuggeritiPerInvito(Candidatura candidatura, SoggettoUtente utenteAutenticato)
	{
		NavigableSet<SoggettoUtente> navigableSet = soggettoUtenteRepository.findByCompetenzasIn(candidatura.getProgetto().getCompetenzas());
		navigableSet = rimuoviSoggettiImpropri(navigableSet, candidatura, utenteAutenticato);
		return navigableSet;
	}

}
