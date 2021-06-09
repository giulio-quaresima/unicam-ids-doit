package it.unicam.ids.doit.repo;

import java.util.NavigableSet;
import java.util.Set;

import it.unicam.ids.doit.model.Competenza;
import it.unicam.ids.doit.model.SoggettoUtente;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
public interface SoggettoUtenteRepository extends LayerSupertype<SoggettoUtente>
{

	/**
	 * @param username
	 * @return
	 */
	SoggettoUtente findOneByAccountUsername(String username);
	
	/**
	 * @param competenzas
	 * @return
	 */
	NavigableSet<SoggettoUtente> findByCompetenzasIn(Set<Competenza> competenzas);

}
