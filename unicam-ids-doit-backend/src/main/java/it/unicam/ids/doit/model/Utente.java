package it.unicam.ids.doit.model;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.SortNatural;

import com.sun.istack.NotNull;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
@Entity
public class Utente extends AbstractEntity<Utente>
{
	private String email;
	private String givenName;
	private String familyName;
	private SortedSet<SoggettoCollettivo> soggettiDiCuiMembro = new TreeSet<>();
	
	private Account account;

	@NotNull
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@NotNull
	public String getGivenName()
	{
		return givenName;
	}

	public void setGivenName(String givenName)
	{
		this.givenName = givenName;
	}

	@NotNull
	public String getFamilyName()
	{
		return familyName;
	}

	public void setFamilyName(String familyName)
	{
		this.familyName = familyName;
	}

	@ManyToMany (targetEntity = SoggettoCollettivo.class)
	@JoinTable (name = "membership", inverseJoinColumns = @JoinColumn (name = "soggetto_collettivo_id"))
	@SortNatural
	public SortedSet<SoggettoCollettivo> getSoggettiDiCuiMembro()
	{
		return soggettiDiCuiMembro;
	}

	public void setSoggettiDiCuiMembro(SortedSet<SoggettoCollettivo> soggettiDiCuiMembro)
	{
		this.soggettiDiCuiMembro = soggettiDiCuiMembro;
	}

	@Embedded
	public Account getAccount()
	{
		return account;
	}

	public void setAccount(Account account)
	{
		this.account = account;
	}

	@Override
	protected Class<Utente> entityType()
	{
		return Utente.class;
	}
	
	@Embeddable
	public static class Account
	{
		private String username;
		private String password;
		
		public String getUsername()
		{
			return username;
		}
		public void setUsername(String username)
		{
			this.username = username;
		}
		public String getPassword()
		{
			return password;
		}
		public void setPassword(String password)
		{
			this.password = password;
		}
	}

}
