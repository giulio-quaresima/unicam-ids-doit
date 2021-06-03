package it.unicam.ids.doit.model.json;

public interface JsonViews
{
	public interface Any {}
	
	public interface Account extends Any {}
	public interface Appartenenza extends Any {}
	public interface Candidatura extends Any {}
	public interface Competenza extends Any {}
	public interface Progetto extends Any {}
	public interface Soggetto extends Any {}
	public interface SoggettoCollettivo extends Soggetto {}
	public interface SoggettoUtente extends Soggetto, Account {}
	
	public interface CandidaturaTree extends Candidatura, Progetto, SoggettoCollettivo, SoggettoUtente {}
	public interface ProgettoTree extends Progetto, Competenza, Candidatura, SoggettoCollettivo, SoggettoUtente {}
	public interface SoggettoTree extends SoggettoCollettivo, SoggettoUtente, Candidatura, Appartenenza {}
}
