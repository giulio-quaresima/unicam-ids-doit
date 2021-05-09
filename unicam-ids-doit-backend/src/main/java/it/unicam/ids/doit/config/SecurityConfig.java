package it.unicam.ids.doit.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.unicam.ids.doit.model.SoggettoUtente;
import it.unicam.ids.doit.model.SoggettoUtente.Account;
import it.unicam.ids.doit.repo.SoggettoUtenteRepository;

@Configuration
@EnableGlobalMethodSecurity (securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	public static final String ROLE_USER = "ROLE_USER";
	
	@Autowired
	private SoggettoUtenteRepository soggettoUtenteRepository;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		// Configurazione fittizia per prototyping rapido...
		InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryUserDetailsManagerConfigurer =
				auth.inMemoryAuthentication();
		// ... assegno a tutti come password lo stesso nome utente
		for (SoggettoUtente soggettoUtente : soggettoUtenteRepository.findAll())
		{
			Account account = soggettoUtente.getAccount();
			inMemoryUserDetailsManagerConfigurer
				.withUser(account.getUsername())
				.password(passwordEncoder().encode(account.getUsername()))
				.roles("USER");
		}
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.formLogin();
		http.cors();
		http.csrf().disable();
	}

	@Override
	@Bean 
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

	@Bean 
	public PasswordEncoder passwordEncoder() 
	{ 
	    return new BCryptPasswordEncoder(); 
	}
	
	@Bean
	public UnsecurityFilter getUnsecurityFilter()
	{
		return new UnsecurityFilter();
	}
	
	/**
	 * Serve per "fingere" l'autenticazione in un contesto stateless
	 * come quello REST, dove l'autenticazione andrà realizzata con JWT;
	 * mantiene in una variabile STATICA (sic!) l'utente autenticato,
	 * che quindi per forza di cose potrà essere unico per tutto il
	 * runtime.
	 * 
	 * @deprecated Orrore utilizzato solo per fast prototyping, da eliminare non appena ci sarà la "vera autenticazione.
	 * 
	 * 
	 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
	 */
	@Deprecated
	public static class UnsecurityFilter implements Filter
	{
		private static Authentication currentAuthentication;

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
		{
			SecurityContextHolder.getContext().setAuthentication(currentAuthentication);
			chain.doFilter(request, response);
		}

		public static Authentication getCurrentAuthentication()
		{
			return currentAuthentication;
		}

		public static void setCurrentAuthentication(Authentication currentAuthentication)
		{
			UnsecurityFilter.currentAuthentication = currentAuthentication;
		}
		
	}

}
