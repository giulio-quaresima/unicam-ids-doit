package it.unicam.ids.doit.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class PersistenceConfig
{	
	@Bean
	public SpringLiquibase springLiquibase(@Autowired DataSource dataSource)
	{
		SpringLiquibase springLiquibase = new SpringLiquibase();
		
		springLiquibase.setDataSource(dataSource);
		springLiquibase.setChangeLog("classpath:it/unicam/ids/doit/model/dbchangelog.xml");
		springLiquibase.setDropFirst(true);
		
		return springLiquibase;
	}
}
