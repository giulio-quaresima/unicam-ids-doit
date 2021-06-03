package it.unicam.ids.doit.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.config.Constants;
import it.unicam.ids.doit.model.Appartenenza;
import it.unicam.ids.doit.model.Appartenenza.Autorizzazione;
import it.unicam.ids.doit.model.SoggettoCollettivo;
import it.unicam.ids.doit.model.SoggettoUtente;
import it.unicam.ids.doit.model.json.JsonViews;
import it.unicam.ids.doit.repo.SoggettoCollettivoRepository;
import it.unicam.ids.doit.repo.SoggettoUtenteRepository;

@RestController
@RequestMapping (Constants.CUSTOM_REST_API_BASE_PATH + SoggettoCollettivoController.BASE_PATH)
// @Secured (value = "ROLE_USER")
@CrossOrigin(origins = Constants.CORS_ALLOWED_ORIGIN)
public class SoggettoCollettivoController
{
	public static final String BASE_PATH = "/soggettoCollettivoes";

	private boolean fastPrototyping = true;
	
	@Autowired
	private SoggettoCollettivoRepository soggettoCollettivoRepository;
	
	@Autowired
	private SoggettoUtenteRepository utenteRepository;
	
	@GetMapping
	@JsonView (JsonViews.SoggettoCollettivo.class)
	public List<SoggettoCollettivo> soggetti()
	{
		return soggettoCollettivoRepository.findAll();
	}
	
	@GetMapping ("/currentUser/autorizzazione/{autorizzazione}")
	@JsonView (JsonViews.SoggettoCollettivo.class)
	public SortedSet<SoggettoCollettivo> soggettiUtente(Principal principal, @PathVariable Autorizzazione autorizzazione)
	{
		if (principal != null)
		{
			SoggettoUtente utente = utenteRepository.findOneByAccountUsername(principal.getName());
			Assert.notNull(utente, "Un utente autenticato dev'essere presente nel DB!");
			return utente
					.getAppartenenze()
					.stream()
					.filter(appartenenza -> appartenenza.getAutorizzazioni().contains(autorizzazione))
					.map(Appartenenza::getOrganizzazione)
					.collect(Collectors.toCollection(TreeSet::new));
		}

		return Collections.emptySortedSet();
	}
	
	@GetMapping ("/{soggettoCollettivo:\\d}")
	@JsonView (JsonViews.SoggettoCollettivo.class)
	public SoggettoCollettivo soggetto(@PathVariable SoggettoCollettivo soggettoCollettivo)
	{
		return soggettoCollettivo;
	}
	
}
