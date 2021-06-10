package it.unicam.ids.doit.model.json;

public interface JsonViews
{

	public interface Any {}
	
	public interface Account extends Any {}
	public interface Appartenenza extends Any {}
	public interface Candidatura extends Any {}
	public interface Competenza extends Any {}
	public interface Invito	extends Any {}
	public interface Progetto extends Competenza {}
	public interface Soggetto extends Any {}
	public interface SoggettoCollettivo extends Soggetto {}
	public interface SoggettoUtente extends Soggetto, Account, Competenza {}
	
	/**
	 * Albero che ha la {@link it.unicam.ids.doit.model.Candidatura} come root.
	 * 
	 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
	 */
	public interface CandidaturaTree extends Candidatura, Progetto, Invito, SoggettoCollettivo, SoggettoUtente {}
	/**
	 * Albero che ha il {@link it.unicam.ids.doit.model.Progetto} come root.
	 * 
	 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
	 */
	public interface ProgettoTree extends Progetto, Candidatura, Invito, SoggettoCollettivo, SoggettoUtente {}
	/**
	 * Albero che ha il {@link it.unicam.ids.doit.model.Soggetto} (Utente o Collettivo) come root.
	 * 
	 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
	 */
	public interface SoggettoTree extends SoggettoCollettivo, SoggettoUtente, Invito, Candidatura, Appartenenza {}
}
