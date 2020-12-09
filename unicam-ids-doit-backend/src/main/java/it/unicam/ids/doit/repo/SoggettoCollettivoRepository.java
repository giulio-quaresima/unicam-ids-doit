package it.unicam.ids.doit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.unicam.ids.doit.model.SoggettoCollettivo;

public interface SoggettoCollettivoRepository extends JpaRepository<SoggettoCollettivo, Integer>, JpaSpecificationExecutor<SoggettoCollettivo>
{

}
