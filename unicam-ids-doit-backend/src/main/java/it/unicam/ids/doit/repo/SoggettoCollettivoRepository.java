package it.unicam.ids.doit.repo;

import java.util.NavigableSet;
import java.util.Set;

import it.unicam.ids.doit.model.Competenza;
import it.unicam.ids.doit.model.SoggettoCollettivo;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
public interface SoggettoCollettivoRepository extends LayerSupertype<SoggettoCollettivo>
{

	/**
	 * @param competenzas
	 * @return
	 */
	NavigableSet<SoggettoCollettivo> findByProgettosCompetenzasIn(Set<Competenza> competenzas);

}
