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

import it.unicam.ids.doit.model.Appartenenza;
import it.unicam.ids.doit.model.SoggettoCollettivo;
import it.unicam.ids.doit.model.SoggettoUtente;
import it.unicam.ids.doit.repo.SoggettoCollettivoRepository;
import it.unicam.ids.doit.repo.SoggettoUtenteRepository;

@RestController
@RequestMapping ("/SoggettoCollettivo")
// @Secured (value = "ROLE_USER")
@CrossOrigin(origins = "http://localhost:4200")
public class SoggettoCollettivoController
{
	private boolean fastPrototyping = true;
	
	@Autowired
	private SoggettoCollettivoRepository soggettoCollettivoRepository;
	
	@Autowired
	private SoggettoUtenteRepository utenteRepository;
	
	@GetMapping
	public List<SoggettoCollettivo> soggetti()
	{
		return soggettoCollettivoRepository.findAll();
	}
	
	@GetMapping ("/currentUser")
	public SortedSet<SoggettoCollettivo> soggettiUtente(Principal principal)
	{
		SoggettoUtente utente = null;
		if (principal != null)
		{
			utente = utenteRepository.findOneByAccountUsername(principal.getName());
			Assert.notNull(utente, "Un utente autenticato dev'essere presente nel DB!");
			return utente.getAppartenenze().stream().map(Appartenenza::getOrganizzazione).collect(Collectors.toCollection(TreeSet::new));
		}
		else if (fastPrototyping)
		{
			return soggettiUtente(new Principal() {
				@Override
				public String getName()
				{
					return "giulio";
				}
			});
		}
		return Collections.emptySortedSet();
	}
	
	@GetMapping ("/{soggettoCollettivo:\\d}")
	public SoggettoCollettivo soggetto(@PathVariable SoggettoCollettivo soggettoCollettivo)
	{
		return soggettoCollettivo;
	}
	
}
