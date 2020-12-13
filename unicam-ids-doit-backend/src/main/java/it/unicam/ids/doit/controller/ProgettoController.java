package it.unicam.ids.doit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.doit.model.Progetto;
import it.unicam.ids.doit.repo.ProgettoRepository;

@RestController
@RequestMapping ("/Progetto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProgettoController
{
	@Autowired
	private ProgettoRepository progettoRepository;
	
	@GetMapping
	public List<Progetto> get()
	{
		return progettoRepository.findAll();
	}
	
	@PutMapping
	public Progetto create(@RequestBody Progetto progetto)
	{
		return progettoRepository.save(progetto);
	}
	
}
