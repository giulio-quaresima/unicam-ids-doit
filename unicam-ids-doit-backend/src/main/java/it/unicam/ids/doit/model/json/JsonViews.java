package it.unicam.ids.doit.model.json;

public interface JsonViews
{
	public interface Any {}
	
	public interface Account extends Any {}
	public interface Appartenenza extends Any {}
	public interface Candidatura extends Any {}
	public interface Competenza extends Any {}
	public interface Progetto extends Any {}
	public interface Soggetto extends Any {}
	public interface SoggettoCollettivo extends Soggetto {}
	public interface SoggettoUtente extends Soggetto, Account {}
	
	/**
	 * Albero che ha la {@link it.unicam.ids.doit.model.Candidatura} come root.
	 * 
	 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
	 */
	public interface CandidaturaTree extends Candidatura, Progetto, SoggettoCollettivo, SoggettoUtente {}
	/**
	 * Albero che ha il {@link it.unicam.ids.doit.model.Progetto} come root.
	 * 
	 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
	 */
	public interface ProgettoTree extends Progetto, Competenza, Candidatura, SoggettoCollettivo, SoggettoUtente {}
	/**
	 * Albero che ha il {@link it.unicam.ids.doit.model.Soggetto} (Utente o Collettivo) come root.
	 * 
	 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
	 */
	public interface SoggettoTree extends SoggettoCollettivo, SoggettoUtente, Candidatura, Appartenenza {}
}
