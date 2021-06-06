package it.unicam.ids.doit.controller;

import java.security.Principal;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.config.Constants;
import it.unicam.ids.doit.dto.CandidaturaDto;
import it.unicam.ids.doit.dto.CandidaturaDtoFactory;
import it.unicam.ids.doit.model.Candidatura;
import it.unicam.ids.doit.model.json.JsonViews;
import it.unicam.ids.doit.repo.CandidaturaRepository;
import it.unicam.ids.doit.repo.SoggettoUtenteRepository;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
@RestController
@RequestMapping (Constants.CUSTOM_REST_API_BASE_PATH + CandidatureController.BASE_PATH)
@CrossOrigin(origins = Constants.CORS_ALLOWED_ORIGIN)
public class CandidatureController
{
	public static final String BASE_PATH = "/candidature";
	
	@Autowired
	private CandidaturaRepository candidaturaRepository;
	
	@Autowired
	private CandidaturaDtoFactory candidaturaDtoFactory;
	
	@Autowired
	private SoggettoUtenteRepository soggettoUtenteRepository;
	
	@GetMapping
	@JsonView (JsonViews.CandidaturaTree.class)
	public Set<CandidaturaDto> list()
	{
		Predicate<CandidaturaDto> filter = CandidaturaDto::isCurrentUserOwner;
		filter = filter.or(CandidaturaDto::isCurrentUserInvitato);
		return candidaturaDtoFactory
				.adaptAll(candidaturaRepository.findAll().stream())
				.filter(filter).collect(Collectors.toSet());
	}
	
	@GetMapping ("/{candidatura:\\d}")
	@JsonView (JsonViews.CandidaturaTree.class)
	public CandidaturaDto get(Candidatura candidatura)
	{
		CandidaturaDto candidaturaDto = candidaturaDtoFactory.adapt(candidatura);
		if (candidaturaDto.isCurrentUserOwner() || candidaturaDto.isCurrentUserInvitato())
		{
			return candidaturaDto;
		}
		throw new SecurityException();
	}
	
	@PostMapping
	@JsonView (JsonViews.CandidaturaTree.class)
	public Candidatura create(@RequestBody Candidatura candidatura)
	{
		return candidaturaRepository.save(candidatura);
	}
}
