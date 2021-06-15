package it.unicam.ids.doit.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
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

import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.config.Constants;
import it.unicam.ids.doit.dto.ProgettoDto;
import it.unicam.ids.doit.dto.ProgettoDtoFactory;
import it.unicam.ids.doit.model.Progetto;
import it.unicam.ids.doit.model.Progetto.Stato;
import it.unicam.ids.doit.model.json.JsonViews;
import it.unicam.ids.doit.repo.ProgettoRepository;
import it.unicam.ids.doit.service.CompetenzaService;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
@RestController
@RequestMapping (Constants.CUSTOM_REST_API_BASE_PATH + ProgettiController.BASE_PATH)
@CrossOrigin(origins = Constants.CORS_ALLOWED_ORIGIN)
public class ProgettiController
{
	public static final String BASE_PATH = "/progetti";
	
	@Autowired
	private CompetenzaService competenzaService;
	
	@Autowired
	private ProgettoRepository progettoRepository;
	
	@Autowired
	private ProgettoDtoFactory progettoDtoFactory;
	
	@GetMapping 
	@JsonView (JsonViews.ProgettoTree.class)
	public List<ProgettoDto> get()
	{
		return progettoDtoFactory
				.adaptAll(progettoRepository.findAll().stream())
				.filter(ProgettoDto::isVisible)
				.collect(Collectors.toList())
				;
	}
	
	@PostMapping
	@JsonView (JsonViews.ProgettoTree.class)
	public Progetto create(@RequestBody Progetto progetto)
	{
		progetto.setStato(Stato.IN_MODIFICA);
		return progettoRepository.save(progetto);
	}
	
	@GetMapping ("/{progetto:\\d}")
	@JsonView (JsonViews.ProgettoTree.class)
	public Progetto get(Progetto progetto)
	{
		return progetto;
	}
	
	@PutMapping ("/{progetto:\\d}")
	@JsonView (JsonViews.ProgettoTree.class)
	public Progetto save(@RequestBody Progetto progetto) throws IOException
	{
		return progettoRepository.save(progetto);
	}
	
	@PostMapping ("/{progetto:\\d}/competenzas")
	@JsonView (JsonViews.ProgettoTree.class)
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
	@JsonView (JsonViews.ProgettoTree.class)
	public Progetto delCompetenza(Progetto progetto, @PathVariable ("tag") String tag)
	{
		progetto.removeCompetenza(tag);
		return progettoRepository.save(progetto);
	}
	
}
