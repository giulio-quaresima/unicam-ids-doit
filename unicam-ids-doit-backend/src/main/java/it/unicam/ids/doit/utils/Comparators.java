package it.unicam.ids.doit.utils;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.function.Supplier;

/**
 * Collezione di singletons di comparators e metodi di utilità.
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
public class Comparators
{
	/**
	 * Comparator che ordina le stringhe in ordine alfabetico, case sensitive, nulls last.
	 */
	public static final Comparator<String> NULLS_LAST_STRING_COMPARATOR_CS = Comparator.nullsLast(String::compareTo);
	
	/**
	 * Comparator che ordina le stringhe in ordine alfabetico, case insensitive, nulls last.
	 */
	public static final Comparator<String> NULLS_LAST_STRING_COMPARATOR_CU = Comparator.nullsLast(String::compareToIgnoreCase);
	
	/**
	 * @param <T>
	 * @param comparator
	 * @return Un {@link Supplier} che crea un {@link NavigableSet} ordinato secondo il comparator.
	 */
	public static <T> Supplier<NavigableSet<T>> navigableSetSupplier(Comparator<T> comparator)
	{
		return () -> new TreeSet<T>(comparator);
	}
}
