package it.unicam.ids.doit.dto;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import it.unicam.ids.doit.model.Candidatura;
import it.unicam.ids.doit.model.SoggettoUtente;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
@Service
public class CandidaturaDtoFactory extends AbstractDtoFactory<Candidatura, CandidaturaDto>
{
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
			return candidaturaDto;
		};
	}

}
