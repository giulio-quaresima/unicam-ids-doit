package it.unicam.ids.doit.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.doit.config.Constants;
import it.unicam.ids.doit.model.Appartenenza.Autorizzazione;
import it.unicam.ids.doit.model.Progetto;
import it.unicam.ids.doit.model.Progetto.Stato;
import it.unicam.ids.doit.model.SoggettoUtente;
import it.unicam.ids.doit.repo.ProgettoRepository;
import it.unicam.ids.doit.repo.SoggettoUtenteRepository;
import it.unicam.ids.doit.service.CompetenzaService;

@RestController
@RequestMapping (Constants.CUSTOM_REST_API_BASE_PATH + ProgettoController.BASE_PATH)
@CrossOrigin(origins = Constants.CORS_ALLOWED_ORIGIN)
public class ProgettoController
{
	public static final String BASE_PATH = "/progettoes";
	
	@Autowired
	private CompetenzaService competenzaService;
	
	@Autowired
	private ProgettoRepository progettoRepository;
	
	@Autowired
	private SoggettoUtenteRepository soggettoUtenteRepository;
	
	@GetMapping 
	public List<Progetto> get(Principal principal)
	{
		Predicate<Progetto> predicate = Progetto::isVisibilePubblicamente;
		if (principal != null)
		{
			SoggettoUtente utenteAutenticato = soggettoUtenteRepository.findOneByAccountUsername(principal.getName());
			if (utenteAutenticato != null)
			{
				predicate = predicate.or(progetto -> {
					return utenteAutenticato.has(Autorizzazione.GESTIONE_PROGETTO, progetto.getOwner());
					});
			}
		}
		return progettoRepository.findAll().stream().filter(predicate).collect(Collectors.toList());
	}
	
	@PostMapping
	public Progetto create(@RequestBody Progetto progetto)
	{
		progetto.setStato(Stato.IN_MODIFICA);
		return progettoRepository.save(progetto);
	}
	
	@GetMapping ("/{progetto:\\d}")
	public Progetto get(Progetto progetto)
	{
		return progetto;
	}
	
	@PutMapping ("/{progetto:\\d}")
	public Progetto save(@RequestBody Progetto progetto) throws IOException
	{
		return progettoRepository.save(progetto);
	}
	
	@PostMapping ("/{progetto:\\d}/competenzas")
	public Progetto addCompetenza(Progetto progetto, @RequestBody String tagCompetenza)
	{
		if (StringUtils.hasText(tagCompetenza))
		{
			progetto.getCompetenzas().addAll(competenzaService.createIfNotExists(tagCompetenza));
			return progettoRepository.save(progetto);
		}
		return progetto;
	}
	
	@DeleteMapping ("/{progetto:\\d}/competenzas/{tag}")
	public Progetto delCompetenza(Progetto progetto, @PathVariable ("tag") String tag)
	{
		progetto.removeCompetenza(tag);
		return progettoRepository.save(progetto);
	}
	
}
