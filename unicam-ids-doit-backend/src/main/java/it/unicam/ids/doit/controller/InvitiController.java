package it.unicam.ids.doit.controller;

import java.security.Principal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.config.Constants;
import it.unicam.ids.doit.model.Invito;
import it.unicam.ids.doit.model.SoggettoUtente;
import it.unicam.ids.doit.model.json.JsonViews;
import it.unicam.ids.doit.repo.InvitoRepository;
import it.unicam.ids.doit.repo.SoggettoUtenteRepository;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
@RestController
@RequestMapping (Constants.CUSTOM_REST_API_BASE_PATH + InvitiController.BASE_PATH)
@CrossOrigin(origins = Constants.CORS_ALLOWED_ORIGIN)
public class InvitiController
{
	public static final String BASE_PATH = "/inviti";
	
	@Autowired
	private SoggettoUtenteRepository soggettoUtenteRepository;
	
	@Autowired
	private InvitoRepository invitoRepository;
	
	@GetMapping ("/{invito:\\d+}")
	@JsonView (JsonViews.SoggettoTree.class)
	public Invito get(@PathVariable Invito invito, Principal principal)
	{
		checkAuthorization(invito, principal);
		return invito;
	}
	
	@PutMapping ("/{invitoDaPath:\\d+}")
	@JsonView (JsonViews.SoggettoTree.class)
	public Invito accettazione(@PathVariable Invito invitoDaPath, @RequestBody Invito invitoDaSalvare, Principal principal)
	{
		checkAuthorization(invitoDaPath, principal);
		return invitoRepository.save(merge(invitoDaPath, invitoDaSalvare));
	}

	private Invito merge(Invito invitoDaPath, Invito invitoDaSalvare)
	{
		invitoDaPath.setAccettazione(invitoDaSalvare.isAccettazione());
		invitoDaPath.setNoteAccettazione(invitoDaSalvare.getNoteAccettazione());
		return invitoDaPath;
	}

	private void checkAuthorization(Invito invito, Principal principal)
	{
		if (principal == null)
		{
			throw new SecurityException("Ma se non sei autenticato, perché tenti di fare ciò? Pensi che io non me ne accorga?");			
		}
		
		SoggettoUtente soggettoUtente = Objects.requireNonNull(
				soggettoUtenteRepository.findOneByAccountUsername(principal.getName()), 
				"Se è autenticato, dev'essere un soggetto utente presente nel DB");
		Assert.state(soggettoUtente.equals(invito.getInvitato()), "Stai tentando di aprire un invito che non ti appartiene");
	}
	
}
