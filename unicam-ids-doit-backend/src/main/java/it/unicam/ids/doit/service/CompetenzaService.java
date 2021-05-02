package it.unicam.ids.doit.service;

import java.util.NavigableSet;

import it.unicam.ids.doit.model.Competenza;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
public interface CompetenzaService
{
	/**
	 * Carica le competenze specificate dalla persistenza,
	 * creando quelle che non esistono già.
	 * 
	 * @param tags I tag corrispondenti alle competenze, eventualmente da creare
	 * 
	 * @return Il set delle competenze, esistenti o nuove, corrispondenti
	 * ai tag richiesti, ed ordinate secondo il loro natural order.
	 */
	public NavigableSet<Competenza> createIfNotExists(String... tags);
}
