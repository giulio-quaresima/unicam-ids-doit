package it.unicam.ids.doit.dto;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import it.unicam.ids.doit.model.AbstractEntity;
import it.unicam.ids.doit.model.SoggettoUtente;
import it.unicam.ids.doit.repo.SoggettoUtenteRepository;

public abstract class AbstractDtoFactory<E extends AbstractEntity<E>, T extends AbstractDto<E>>
{
	@Autowired
	protected SoggettoUtenteRepository soggettoUtenteRepository;
	
	public T adapt(E entity)
	{
		return adapter().apply(entity);
	}
	
	public Stream<T> adaptAll(Stream<E> entities)
	{
		return entities.map(adapter());
	}	
	
	public abstract Function<E,T> adapter();
	
	protected SoggettoUtente findCurrentUser()
	{
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication != null)
		{
			return Objects.requireNonNull(soggettoUtenteRepository.findOneByAccountUsername(authentication.getName()), "Se si è autenticato, deve esistere per forza nel DB!!!");
		}
		return null;
	}
}
