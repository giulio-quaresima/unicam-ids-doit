package it.unicam.ids.doit.model;

import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import liquibase.integration.spring.SpringLiquibase;

@SpringBootApplication (scanBasePackageClasses = Bootstrap.class)
public class Bootstrap
{
	public static void main(String[] args)
	{
		SpringApplication.run(Bootstrap.class, args);
		System.out.println("\n\n\nPlease press ENTER to shutdown gracefully");
		try (Scanner scanner = new Scanner(System.in))
		{
			scanner.nextLine();
		}
		System.out.println("Bye!");
		System.exit(0);
	}
	
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
