package it.unicam.ids.doit;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Bootstrapper dell'applicazione, che permette anche di terminare in modo 
 * "graceful" l'applicazione. Da CLI è sufficiente un CTRL+C, ma purtroppo
 * in certi ambienti (notabilmente Eclipse) l'unico modo per uscire è
 * premere il tasto di stop della console (quadrato rosso) il che uccide
 * il processo senza dargli modo di terminare in modo corretto (in
 * particolare così non vengono richiamati gli shutdown hooks registrati
 * da Spring Boot).
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--unipg.it, giulio.quaresima--at--gmail.com)
 */
@SpringBootApplication (scanBasePackageClasses = Bootstrap.class)
public class Bootstrap
{
	public static void main(String[] args)
	{
		ConfigurableApplicationContext app = SpringApplication.run(Bootstrap.class, args);
		
		System.out.println("\n\n\n"
				+ "*********************************************\n"
				+ "* Please press ENTER to shutdown gracefully *\n"
				+ "*********************************************");
		try (Scanner scanner = new Scanner(System.in))
		{
			if (scanner.hasNextLine())
			{
				scanner.nextLine();
			}
		}
		
		int exitCode = SpringApplication.exit(app);
		// int exitCode = SpringApplication.exit(app, () -> Integer.MAX_VALUE); // To test the error message.
		if (exitCode == 0)
		{
			System.out.println("\nThank you for letting me shutdown gracefully. Bye!");
		}
		else
		{
			System.out.println("\nThank you for letting me shutdown gracefully.");
			System.out.printf("Anyway, something went wrong, exit code: %d.\n", exitCode);
			System.out.println("Bye!");
		}
	}
}
