package it.unicam.ids.doit.repo;

import it.unicam.ids.doit.model.SoggettoUtente;

public interface UtenteRepository extends LayerSupertype<SoggettoUtente>
{

	SoggettoUtente findOneByAccountUsername(String username);

}
