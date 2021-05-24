package it.unicam.ids.doit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.doit.config.Constants;
import it.unicam.ids.doit.repo.CandidaturaRepository;

@RestController
@RequestMapping (Constants.CUSTOM_REST_API_BASE_PATH + CandidatureController.BASE_PATH)
@CrossOrigin(origins = Constants.CORS_ALLOWED_ORIGIN)
public class CandidatureController
{
	public static final String BASE_PATH = "/candidature";
	
	@SuppressWarnings("unused")
	@Autowired
	private CandidaturaRepository candidaturaRepository;
	
	 
	
}
