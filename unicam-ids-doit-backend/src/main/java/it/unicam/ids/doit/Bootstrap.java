package it.unicam.ids.doit;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import it.unicam.ids.doit.config.PersistenceConfig;

@SpringBootApplication (scanBasePackageClasses = Bootstrap.class)
@Import ({PersistenceConfig.class})
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
}
