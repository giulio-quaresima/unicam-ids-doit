package it.unicam.ids.doit.controller;

import java.util.NavigableSet;
import java.util.Optional;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unicam.ids.doit.model.Competenza;
import it.unicam.ids.doit.repo.CompetenzaRepository;

@BasePathAwareController
@RepositoryRestController
@RequestMapping ("/custom/competenze")
public class CompetenzeController
{
	@Autowired
	private CompetenzaRepository competenzaRepository;
	
	@ResponseBody
	@Transactional
	@PutMapping ("/createIfNotExists")
	public NavigableSet<Competenza> createIfNotExists(@RequestBody Tags tags)
	{
		NavigableSet<Competenza> competenzas = new TreeSet<>();
		for (String tag : tags.tags)
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
	
	public static class Tags
	{
		private String[] tags;

		public String[] getTags()
		{
			return tags;
		}

		public void setTags(String[] tags)
		{
			this.tags = tags;
		}
	}
	
}
