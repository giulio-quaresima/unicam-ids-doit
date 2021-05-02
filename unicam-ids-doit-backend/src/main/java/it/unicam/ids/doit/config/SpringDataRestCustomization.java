package it.unicam.ids.doit.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import it.unicam.ids.doit.model.Progetto;

@Component
public class SpringDataRestCustomization implements RepositoryRestConfigurer
{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors)
	{
		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
		cors.addMapping("/**").allowedOrigins(Constants.CORS_ALLOWED_ORIGIN);
		config
			.setBasePath(Constants.SPRING_DATA_REST_API_BASE_PATH)
			.exposeIdsFor(Progetto.class);
	}
	
}
