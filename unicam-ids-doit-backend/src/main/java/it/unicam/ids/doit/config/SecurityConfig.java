package it.unicam.ids.doit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
		http.csrf().disable().authorizeRequests().antMatchers("/login*").permitAll();
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

}
