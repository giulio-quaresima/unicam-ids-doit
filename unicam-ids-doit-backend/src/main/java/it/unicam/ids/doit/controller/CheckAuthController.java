package it.unicam.ids.doit.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.doit.model.Utente;
import it.unicam.ids.doit.repo.UtenteRepository;

@RestController
@RequestMapping
public class CheckAuthController
{
	@Autowired
	private UtenteRepository utenteRepository;
	
	@GetMapping ("/AuthStatus")
	@ResponseBody
	public AuthStatus isAuthenticated(Principal principal)
	{
		Utente utente = null;
		if (principal != null)
		{
			utente = utenteRepository.findOneByAccountUsername(principal.getName());
		}
		return new AuthStatus(utente);
	}
	
	public static class AuthStatus
	{
		private final boolean authenticated;
		private final Utente utente;
		
		public AuthStatus(Utente utente)
		{
			super();
			this.authenticated = utente != null;
			this.utente = utente;
		}

		public boolean isAuthenticated()
		{
			return authenticated;
		}

		public Utente getUtente()
		{
			return utente;
		}

	}
}
