package it.unicam.ids.doit.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.doit.config.Constants;

@RestController
@RequestMapping (Constants.CUSTOM_REST_API_BASE_PATH + CompetenzeController.BASE_PATH)
@CrossOrigin(origins = Constants.CORS_ALLOWED_ORIGIN)
public class CompetenzeController
{
	public static final String BASE_PATH = "/progettoes";
}
