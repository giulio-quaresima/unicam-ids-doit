package it.unicam.ids.doit.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unicam.ids.doit.model.Competenza;
import it.unicam.ids.doit.model.Progetto;
import it.unicam.ids.doit.model.Progetto.Stato;
import it.unicam.ids.doit.repo.CompetenzaRepository;
import it.unicam.ids.doit.repo.ProgettoRepository;

@RepositoryRestController
@RequestMapping ("/custom/progettoes")
@CrossOrigin(origins = "http://localhost:4200")
public class ProgettoController
{
	@Autowired
	private CompetenzaRepository competenzaRepository;
	
	@Autowired
	private ProgettoRepository progettoRepository;
	
	@GetMapping
	public List<Progetto> get()
	{
		return progettoRepository.findAll();
	}
	
	@PutMapping ("/{progetto:\\d}")
	@ResponseBody
	@Transactional
	public Progetto save(@PathVariable @RequestBody Progetto progetto, HttpServletRequest request) throws IOException
	{
		/* 
		 * FIXME Wait for having a better understand of the right way
		 * to interact between Angular and Spring Data Rest 
		 */
		String line;
		BufferedReader bufferedReader = request.getReader();
		while ((line = bufferedReader.readLine()) != null)
		{
			System.out.println(line);
		}
		System.out.println(progetto.getCompetenzeTags());
		for (String tag : progetto.getCompetenzeTags())
		{
			Competenza competenza = new Competenza();
			competenza.setTag(tag);
			Optional<Competenza> optional = competenzaRepository.findOne(Example.of(competenza));
			if (optional.isEmpty())
			{
				competenza = competenzaRepository.save(competenza);
			}
			else
			{
				competenza = optional.get();
			}
			progetto.getCompetenze().add(competenza);
		}
		progetto.setStato(Stato.IN_MODIFICA);
		return progettoRepository.save(progetto);
	}
	
}
