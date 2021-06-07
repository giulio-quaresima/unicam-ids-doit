package it.unicam.ids.doit.model;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;

import org.hibernate.annotations.SortNatural;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.model.Appartenenza.Autorizzazione;
import it.unicam.ids.doit.model.json.JsonViews;

@Entity
@DiscriminatorValue (value = "U")
@SecondaryTable (name = SoggettoUtente.SECONDARY_TABLE)
@JsonView(JsonViews.SoggettoUtente.class)
public class SoggettoUtente extends Soggetto<SoggettoUtente>
{
	public static final String SECONDARY_TABLE = "account";
	
	@Basic
	private String cognome;
	
	@Basic
	private String nome;

	@Embedded
	private Account account;
		
	@OneToMany (mappedBy = "membro")
	@JsonManagedReference
	private Set<Appartenenza> appartenenze = new HashSet<Appartenenza>();
	
	@ManyToMany
	@JoinTable (name = "soggetto_utente_competenza", inverseJoinColumns = @JoinColumn (name = "id_competenza"), joinColumns = @JoinColumn (name = "id_soggetto_utente"))
	@SortNatural
	private SortedSet<Competenza> competenzas = new TreeSet<Competenza>();
	
	@JsonView (JsonViews.SoggettoTree.class)
	public Set<Candidatura> getCandidatureAll()
	{
		return Stream.concat(
					getCandidature().stream(), 
					getAppartenenze()
					.stream()
					.filter(a -> a.getAutorizzazioni().contains(Autorizzazione.CANDIDATURA))
					.map(Appartenenza::getOrganizzazione)
					.flatMap(s -> s.getCandidature().stream()))
				.collect(Collectors.toSet());
	}

	public String getCognome()
	{
		return cognome;
	}
	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}

	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Account getAccount()
	{
		return account;
	}
	public void setAccount(Account account)
	{
		this.account = account;
	}
	
	@JsonView (JsonViews.SoggettoTree.class)
	public Set<Appartenenza> getAppartenenze()
	{
		return appartenenze;
	}
	public void setAppartenenze(Set<Appartenenza> appartenenze)
	{
		this.appartenenze = appartenenze;
	}
	
	public SortedSet<Competenza> getCompetenzas()
	{
		return competenzas;
	}
	public void setCompetenzas(SortedSet<Competenza> competenzas)
	{
		this.competenzas = competenzas;
	}

	public boolean has(Autorizzazione autorizzazione, SoggettoCollettivo soggettoCollettivo)
	{
		if (soggettoCollettivo != null && autorizzazione != null)
		{
			return getAppartenenze()
					.stream()
					.filter(appartenenza -> appartenenza.getAutorizzazioni().contains(autorizzazione))
					.map(Appartenenza::getOrganizzazione)
					.anyMatch(s -> soggettoCollettivo.equals(s))
					;
		}
		return false;
	}
	
	@Override
	public String getDenominazione()
	{
		return getCognome() + ' ' + getNome();
	}

	@Override
	protected Class<SoggettoUtente> entityType()
	{
		return SoggettoUtente.class;
	}
	
	@Embeddable
	@JsonView(JsonViews.Account.class)
	public static class Account
	{
		@Column (table = SECONDARY_TABLE)
		private String username;

		@Column (table = SECONDARY_TABLE)
		private String email;
		
		public String getUsername()
		{
			return username;
		}
		public void setUsername(String username)
		{
			this.username = username;
		}

		public String getEmail()
		{
			return email;
		}
		public void setEmail(String email)
		{
			this.email = email;
		}
	}

}
