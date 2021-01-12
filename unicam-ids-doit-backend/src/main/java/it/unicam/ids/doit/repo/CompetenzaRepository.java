package it.unicam.ids.doit.repo;

import java.util.SortedSet;

import it.unicam.ids.doit.model.Competenza;

public interface CompetenzaRepository extends LayerSupertype<Competenza>
{
	SortedSet<Competenza> findAllByTagIn(String[] tags);
}
