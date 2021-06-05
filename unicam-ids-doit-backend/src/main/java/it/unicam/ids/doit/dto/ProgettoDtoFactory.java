package it.unicam.ids.doit.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import it.unicam.ids.doit.model.Appartenenza.Autorizzazione;
import it.unicam.ids.doit.model.Progetto;
import it.unicam.ids.doit.model.SoggettoUtente;
import it.unicam.ids.doit.repo.ProgettoRepository;
import it.unicam.ids.doit.repo.SoggettoUtenteRepository;

@Service
public class ProgettoDtoFactory extends AbstractDtoFactory<Progetto, ProgettoDto>
{
	@Autowired
	private ProgettoRepository progettoRepository;
	
	@Autowired
	private SoggettoUtenteRepository soggettoUtenteRepository;
	
	@Override
	public Function<Progetto, ProgettoDto> adapter()
	{
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		Set<Progetto> progettiOwned = new HashSet<>();
		if (authentication != null)
		{
			SoggettoUtente utenteAutenticato = soggettoUtenteRepository.findOneByAccountUsername(authentication.getName());
			Assert.notNull(utenteAutenticato, "Se si è autenticato, deve esistere nel DB");
			progettoRepository
				.findAll()
				.stream()
				.filter(progetto -> utenteAutenticato.has(Autorizzazione.GESTIONE_PROGETTO, progetto.getProponente()))
				.forEach(progettiOwned::add);
				;
		}
		return progetto -> {
			ProgettoDto progettoDto = new ProgettoDto(progetto);
			progettoDto.setCurrentUserOwner(progettiOwned.contains(progetto));
			return progettoDto;
		};
	}

}
