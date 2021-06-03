package it.unicam.ids.doit.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.config.Constants;
import it.unicam.ids.doit.config.SecurityConfig.UnsecurityFilter;
import it.unicam.ids.doit.model.SoggettoUtente;
import it.unicam.ids.doit.model.json.JsonViews;
import it.unicam.ids.doit.repo.SoggettoUtenteRepository;

@RestController
@RequestMapping (Constants.CUSTOM_REST_API_BASE_PATH + AuthController.BASE_PATH)
@CrossOrigin(origins = Constants.CORS_ALLOWED_ORIGIN)
public class AuthController
{
	public static final String BASE_PATH = "/auth";
	
	@Autowired
	private SoggettoUtenteRepository soggettoUtenteRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UnsecurityFilter unsecurityFilter;
	
	@GetMapping
	@JsonView (JsonViews.SoggettoUtente.class)
	public Response<AuthStatus> isAuthenticated(Principal principal)
	{
		SoggettoUtente utente = null;
		if (principal != null)
		{
			utente = soggettoUtenteRepository.findOneByAccountUsername(principal.getName());
		}
		return Response.of(new AuthStatus(utente));
	}
	
	@RequestMapping ("/login")
	@JsonView (JsonViews.SoggettoUtente.class)
	public Response<AuthStatus> login(
			@RequestParam ("username") String username,
			@RequestParam ("password") String password)
	{
		try
		{
			unsecurityFilter.setCurrentAuthentication(authenticationManager.authenticate(
								new UsernamePasswordAuthenticationToken(username, password)));
			return Response.of(new AuthStatus(soggettoUtenteRepository.findOneByAccountUsername(username)));
		}
		catch (AuthenticationException e)
		{
			return Response.of(new AuthStatus(null));
		}
	}
	
	@RequestMapping ("/logout")
	@JsonView (JsonViews.SoggettoUtente.class)
	public Response<AuthStatus> logout()
	{
		unsecurityFilter.setCurrentAuthentication(null);		
		return Response.of(new AuthStatus(null));
	}
	
	/**
	 * @deprecated Solo per fast prototyping, eliminare questo metodo
	 * non appena si sarà sviluppata una vera autenticazione.
	 */
	@GetMapping ("/users")
	@JsonView (JsonViews.SoggettoUtente.class)
	@Deprecated
	public Response<List<SoggettoUtente>> users()
	{
		return Response.of(soggettoUtenteRepository.findAll());
	}
	
	@JsonView (JsonViews.SoggettoUtente.class)
	public static class AuthStatus
	{
		private final boolean authenticated;
		private final SoggettoUtente utente;
		
		public AuthStatus(SoggettoUtente utente)
		{
			super();
			this.authenticated = utente != null;
			this.utente = utente;
		}

		public boolean isAuthenticated()
		{
			return authenticated;
		}

		public SoggettoUtente getUtente()
		{
			return utente;
		}

	}
}
