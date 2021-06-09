package it.unicam.ids.doit.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.model.json.JsonViews;

/**
 * 
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
@Entity
@JsonView(JsonViews.Candidatura.class)
public class Candidatura extends AbstractEntity<Candidatura>
{
	@Basic
	@NotNull
	private String autopromozione;

	@Basic
	private boolean inviata = false;
	
	@Basic
	private boolean selezionata = false;
	
	@Basic
	private String incarico;
	
	@ManyToOne (optional = false)
	@NotNull
	@JsonView (JsonViews.CandidaturaTree.class)
	private Soggetto<?> soggetto;
	
	@OneToMany (mappedBy = "candidatura")
	@JsonView (JsonViews.CandidaturaTree.class)
	private Set<Invito> inviti = new HashSet<>();
	
	@ManyToOne (optional = false)
	@NotNull
	@JsonView (JsonViews.CandidaturaTree.class)
	private Progetto progetto;

	/**
	 * Campo descrittivo con il quale il Progettista, 
	 * unitamente ad eventuali documenti allegati (Documento), 
	 * promuove la propria candidatura al Progetto, 
	 * cercando di convincere il Proponente di avere l'esperienza, 
	 * le competenze e le capacità per farlo. 
	 * Può essere implementato come override dell'attributo descrizione : string di Descrizione
	 */
	public String getAutopromozione()
	{
		return autopromozione;
	}
	public void setAutopromozione(String autopromozione)
	{
		this.autopromozione = autopromozione;
	}

	/**
	 * La candidatura può avere due stati, in bozza (false) e inviata (true).
	 */
	public boolean isInviata()
	{
		return inviata;
	}
	public void setInviata(boolean inviata)
	{
		this.inviata = inviata;
	}

	/**
	 * Se true, significa che questa candidatura, 
	 * comprensiva del capofila e degli eventuali 
	 * altri progettisti invitati dal capofila che abbiano accettata, 
	 * è stata selezionata dal proponente per implementare il progetto.
	 */
	public boolean isSelezionata()
	{
		return selezionata;
	}
	public void setSelezionata(boolean selezionata)
	{
		this.selezionata = selezionata;
	}
	
	/**
	 * La descrizione dell'incarico assegnato al Progettista 
	 * come parte della cordata individuata dal Proponente 
	 * nella realizzazione del Progetto.
	 */
	public String getIncarico()
	{
		return incarico;
	}
	public void setIncarico(String incarico)
	{
		this.incarico = incarico;
	}
	
	/**
	 * Soggetto che si candida, può essere sia un
	 * soggetto individuale che collettivo.
	 */
	public Soggetto<?> getSoggetto()
	{
		return soggetto;
	}
	public void setSoggetto(Soggetto<?> soggetto)
	{
		this.soggetto = soggetto;
	}

	/**
	 * @return
	 */
	public Set<Invito> getInviti()
	{
		return inviti;
	}
	public void setInviti(Set<Invito> inviti)
	{
		this.inviti = inviti;
	}
	
	/**
	 * Facility per ottenere direttamente il set degli invitati
	 * passando per {@link #getInviti()}.
	 * 
	 * @return Un set eventualmente vuoto, mai <code>null</code>.
	 */
	@JsonView (JsonViews.CandidaturaTree.class)
	public Set<Soggetto<?>> getInvitati()
	{
		return getInviti().stream().map(Invito::getInvitato).collect(Collectors.toSet());
	}
	
	/**
	 * Il progetto a cui ci si candida.
	 */
	public Progetto getProgetto()
	{
		return progetto;
	}
	public void setProgetto(Progetto progetto)
	{
		this.progetto = progetto;
	}

	@Override
	protected Class<Candidatura> entityType()
	{
		return Candidatura.class;
	}

}
