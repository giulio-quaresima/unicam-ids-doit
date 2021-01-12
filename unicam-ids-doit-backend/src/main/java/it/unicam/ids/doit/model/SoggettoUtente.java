package it.unicam.ids.doit.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;

@Entity
@DiscriminatorValue (value = "U")
@SecondaryTable (name = SoggettoUtente.SECONDARY_TABLE)
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
	private Set<Appartenenza> appartenenze = new HashSet<Appartenenza>();

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
	
	public Set<Appartenenza> getAppartenenze()
	{
		return appartenenze;
	}
	public void setAppartenenze(Set<Appartenenza> appartenenze)
	{
		this.appartenenze = appartenenze;
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
