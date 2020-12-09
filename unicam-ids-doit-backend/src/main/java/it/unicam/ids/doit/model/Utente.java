package it.unicam.ids.doit.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
	private Set<SoggettoCollettivo> soggettiDiCuiMembro = new HashSet<>();

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
	@JoinTable (name = "membership")
	public Set<SoggettoCollettivo> getSoggettiDiCuiMembro()
	{
		return soggettiDiCuiMembro;
	}

	public void setSoggettiDiCuiMembro(Set<SoggettoCollettivo> soggettiDiCuiMembro)
	{
		this.soggettiDiCuiMembro = soggettiDiCuiMembro;
	}

	@Override
	protected Class<Utente> entityType()
	{
		return Utente.class;
	}

}
