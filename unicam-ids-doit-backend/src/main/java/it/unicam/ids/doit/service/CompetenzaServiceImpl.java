package it.unicam.ids.doit.service;

import java.util.NavigableSet;
import java.util.Optional;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unicam.ids.doit.model.Competenza;
import it.unicam.ids.doit.repo.CompetenzaRepository;

@Service
@Transactional
public class CompetenzaServiceImpl implements CompetenzaService
{
	@Autowired
	private CompetenzaRepository competenzaRepository;

	@Override
	public NavigableSet<Competenza> createIfNotExists(String... tags)
	{
		NavigableSet<Competenza> competenzas = new TreeSet<>();
		for (String tag : tags)
		{
			Competenza competenza = new Competenza();
			competenza.setTag(tag);
			Optional<Competenza> optional = competenzaRepository.findOne(Example.of(competenza));
			if (optional.isEmpty())
			{
				competenza = competenzaRepository.save(competenza);
			}
			else
			{
				competenza = optional.get();
			}
			competenzas.add(competenza);
		}
		return competenzas;
	}

}
