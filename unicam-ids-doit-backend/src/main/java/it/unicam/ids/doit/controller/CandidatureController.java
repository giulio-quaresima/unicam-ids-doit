package it.unicam.ids.doit.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.config.Constants;
import it.unicam.ids.doit.model.Candidatura;
import it.unicam.ids.doit.model.SoggettoUtente;
import it.unicam.ids.doit.model.json.JsonViews;
import it.unicam.ids.doit.repo.CandidaturaRepository;
import it.unicam.ids.doit.repo.SoggettoUtenteRepository;

@RestController
@RequestMapping (Constants.CUSTOM_REST_API_BASE_PATH + CandidatureController.BASE_PATH)
@CrossOrigin(origins = Constants.CORS_ALLOWED_ORIGIN)
public class CandidatureController
{
	public static final String BASE_PATH = "/candidature";
	
	@Autowired
	private CandidaturaRepository candidaturaRepository;
	
	@Autowired
	private SoggettoUtenteRepository soggettoUtenteRepository;
	
	@GetMapping
	@JsonView (JsonViews.CandidaturaTree.class)
	public Set<Candidatura> list(Principal principal)
	{
		if (principal != null)
		{
			SoggettoUtente soggettoUtente = soggettoUtenteRepository.findOneByAccountUsername(principal.getName());
			if (soggettoUtente != null)
			{
				return soggettoUtente.getCandidatureAll();
			}
		}
		return Collections.emptySet();
	}
	
	@PostMapping
	@JsonView (JsonViews.CandidaturaTree.class)
	public Candidatura create(@RequestBody Candidatura candidatura)
	{
		return candidaturaRepository.save(candidatura);
	}
}
