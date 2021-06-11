package it.unicam.ids.doit.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.ids.doit.model.Appartenenza.Autorizzazione;
import it.unicam.ids.doit.model.Candidatura;
import it.unicam.ids.doit.model.Progetto;
import it.unicam.ids.doit.model.Progetto.Stato;
import it.unicam.ids.doit.model.SoggettoUtente;
import it.unicam.ids.doit.repo.ProgettoRepository;

@Service
public class ProgettoDtoFactory extends AbstractDtoFactory<Progetto, ProgettoDto>
{
	@Autowired
	private ProgettoRepository progettoRepository;
	
	@Override
	public Function<Progetto, ProgettoDto> adapter()
	{
		Set<Progetto> progettiOwned = new HashSet<>();
		Set<Progetto> progettiCandidato = new HashSet<>();
		SoggettoUtente utenteAutenticato = findCurrentUser();
		if (utenteAutenticato != null)
		{
			progettoRepository
				.findAll()
				.stream()
				.filter(progetto -> utenteAutenticato.has(Autorizzazione.GESTIONE_PROGETTO, progetto.getProponente()))
				.forEach(progettiOwned::add);
				;
			utenteAutenticato
				.getCandidatureAll()
				.stream()
				.map(Candidatura::getProgetto)
				.forEach(progettiCandidato::add);
				;
		}
		return progetto -> {
			ProgettoDto progettoDto = new ProgettoDto(progetto);
			progettoDto.setCurrentUserOwner(progettiOwned.contains(progetto));
			progettoDto.setCurrentUserPuoCandidarsi(
					Stato.PUBBLICATO.equals(progetto.getStato())
					&&
					utenteAutenticato != null
					&&
					!progettiCandidato.contains(progetto)
					);
			return progettoDto;
		};
	}

}
