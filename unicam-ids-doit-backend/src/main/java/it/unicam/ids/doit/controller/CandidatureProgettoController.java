package it.unicam.ids.doit.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.config.Constants;
import it.unicam.ids.doit.dto.CandidatureProgetto;
import it.unicam.ids.doit.model.Appartenenza;
import it.unicam.ids.doit.model.Appartenenza.Autorizzazione;
import it.unicam.ids.doit.model.json.JsonViews;
import it.unicam.ids.doit.model.Candidatura;
import it.unicam.ids.doit.model.Soggetto;
import it.unicam.ids.doit.model.SoggettoUtente;
import it.unicam.ids.doit.repo.ProgettoRepository;
import it.unicam.ids.doit.repo.SoggettoUtenteRepository;

@RestController
@RequestMapping (Constants.CUSTOM_REST_API_BASE_PATH + CandidatureProgettoController.BASE_PATH)
@CrossOrigin(origins = Constants.CORS_ALLOWED_ORIGIN)
public class CandidatureProgettoController
{
	public static final String BASE_PATH = "/candidatureProgetto";
	
	@Autowired
	private ProgettoRepository progettoRepository;
	
	@Autowired
	private SoggettoUtenteRepository soggettoUtenteRepository;
	
	@GetMapping 
	@JsonView (JsonViews.SoggettoUtente.class)
	public List<CandidatureProgetto> get(Principal principal)
	{
		Set<Candidatura> candidatureUtente = new HashSet<Candidatura>();
		if (principal != null)
		{
			Set<Soggetto<?>> soggettos = new HashSet<Soggetto<?>>();
			SoggettoUtente utenteAutenticato = soggettoUtenteRepository.findOneByAccountUsername(principal.getName());
			if (utenteAutenticato != null)
			{
				// Aggiungo l'utente come individuo
				soggettos.add(utenteAutenticato); 
				
				// Quindi aggiungo tutti i soggetti collettivi di cui l'utente è membro
				// con l'autorizzazione a gestirne le candidature.
				utenteAutenticato
					.getAppartenenze()
					.stream()
					.filter(appartenenza -> appartenenza.getAutorizzazioni().contains(Autorizzazione.CANDIDATURA))
					.map(Appartenenza::getOrganizzazione)
					.forEach(soggettos::add);
			}
			
			// Infine carico nel set tutte le candidature
			// gestite sia come utente singolo che come
			// soggetto collettivo.
			soggettos.stream().flatMap(soggetto -> soggetto.getCandidature().stream()).forEach(candidatureUtente::add);

		}
		return progettoRepository
				.findAll()
				.stream()
				.map(progetto -> new CandidatureProgetto(progetto, candidatureUtente))
				.collect(Collectors.toList());
	}
	
}
