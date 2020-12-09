package it.unicam.ids.doit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.doit.model.SoggettoCollettivo;
import it.unicam.ids.doit.repo.SoggettoCollettivoRepository;

@RestController
@RequestMapping ("/SoggettoCollettivo")
public class SoggettoCollettivoController
{
	@Autowired
	private SoggettoCollettivoRepository soggettoCollettivoRepository;
	
	@GetMapping
	@ResponseBody
	public List<SoggettoCollettivo> soggetti()
	{
		return soggettoCollettivoRepository.findAll();
	}
	
	@GetMapping ("/{soggettoCollettivo:\\d}")
	@ResponseBody
	public SoggettoCollettivo soggetto(@PathVariable SoggettoCollettivo soggettoCollettivo)
	{
		return soggettoCollettivo;
	}
	
}
