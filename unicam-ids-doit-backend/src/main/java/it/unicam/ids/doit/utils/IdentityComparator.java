package it.unicam.ids.doit.utils;

import java.util.Comparator;
import java.util.Objects;

/**
 * Comparator to be used as a fallback of other more specified comparators
 * to adhere to the "consistent with equals" contract.
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
public class IdentityComparator<T> implements Comparator<T>
{

	@Override
	public int compare(T o1, T o2)
	{
		if (Objects.equals(o1, o2))
		{
			return 0;
		}
		
		if (o1 != null)
		{
			if (o2 == null)
			{
				return -1;
			}
			return System.identityHashCode(o1) - System.identityHashCode(o2); // FIXME identityHashCode is not necessarily unique.
		}

		return 1;
	}
	
}
