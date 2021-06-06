package it.unicam.ids.doit.controller;

import javax.naming.AuthenticationException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import it.unicam.ids.doit.config.SecurityConfig.UnsecurityFilter;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
@RestControllerAdvice
public class ControllersAdvice
{
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ControllersAdvice.class);
	
	@Autowired
	private UnsecurityFilter unsecurityFilter;
	
	@ExceptionHandler (AuthenticationException.class)
	@ResponseStatus (code = HttpStatus.UNAUTHORIZED)
	public String authenticationException(AuthenticationException authenticationException)
	{
		unsecurityFilter.setCurrentAuthentication(null);	
		LOGGER.error(authenticationException.getMessage(), authenticationException);
		return "authentication failed";
	}
	
	@ExceptionHandler (SecurityException.class)
	@ResponseStatus (code = HttpStatus.UNAUTHORIZED)
	public String securityException(SecurityException securityException)
	{
		LOGGER.error(securityException.getMessage(), securityException);
		return "authentication failed";
	}
	
}