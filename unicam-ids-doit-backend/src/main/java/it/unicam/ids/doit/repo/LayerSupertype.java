package it.unicam.ids.doit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import it.unicam.ids.doit.model.AbstractEntity;

@NoRepositoryBean
public interface LayerSupertype<E extends AbstractEntity<?>> extends JpaRepository<E, Integer>, JpaSpecificationExecutor<E>
{

}
