package it.unicam.ids.doit.repo;

import it.unicam.ids.doit.model.SoggettoUtente;

public interface SoggettoUtenteRepository extends LayerSupertype<SoggettoUtente>
{

	SoggettoUtente findOneByAccountUsername(String username);

}
