package it.unicam.ids.doit.repo;

import it.unicam.ids.doit.model.Utente;

public interface UtenteRepository extends LayerSupertype<Utente>
{

	Utente findOneByAccountUsername(String username);

}
