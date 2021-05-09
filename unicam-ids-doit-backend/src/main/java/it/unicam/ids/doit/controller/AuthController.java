package it.unicam.ids.doit.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.doit.config.Constants;
import it.unicam.ids.doit.model.SoggettoUtente;
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
	
	@GetMapping
	public AuthStatus isAuthenticated(Principal principal)
	{
		SoggettoUtente utente = null;
		if (principal != null)
		{
			utente = soggettoUtenteRepository.findOneByAccountUsername(principal.getName());
		}
		return new AuthStatus(utente);
	}
	
	@RequestMapping ("/login")
	public String login(
			@RequestParam ("username") String username,
			@RequestParam ("password") String password)
	{
		// L'eccezione AuthenticationException viene gestita da ControllersAdvice
		SecurityContextHolder
			.getContext()
			.setAuthentication(
					authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(username, password)));
		return "authenticated";
	}
	
	@RequestMapping ("/logout")
	public String logout()
	{
		SecurityContextHolder.getContext().setAuthentication(null);		
		return "LOGOUT SUCCESSFUL";
	}
	
	/**
	 * @deprecated Solo per fast prototyping, eliminare questo metodo
	 * non appena si sarà sviluppata una vera autenticazione.
	 */
	@GetMapping ("/users")
	@Deprecated
	public List<SoggettoUtente> users()
	{
		return soggettoUtenteRepository.findAll();
	}
	
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
