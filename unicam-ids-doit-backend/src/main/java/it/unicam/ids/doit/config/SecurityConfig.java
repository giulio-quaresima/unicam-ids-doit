package it.unicam.ids.doit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity (securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	public static final String ROLE_USER = "ROLE_USER";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		// Configurazione fittizia per prototyping rapido.
		auth
		.inMemoryAuthentication().withUser("giulio").password(passwordEncoder().encode("changeme")).roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.formLogin();
		http.csrf().disable().authorizeRequests().antMatchers("/login*").permitAll();
	}

	@Bean 
	public PasswordEncoder passwordEncoder() 
	{ 
	    return new BCryptPasswordEncoder(); 
	}

}
