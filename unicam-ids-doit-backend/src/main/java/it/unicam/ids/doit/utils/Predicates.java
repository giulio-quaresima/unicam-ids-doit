package it.unicam.ids.doit.utils;

import java.util.function.Predicate;

public class Predicates
{
	public static <T> Predicate<T> alwaysTruePredicate()
	{
		return t -> Boolean.TRUE.booleanValue();
	}
	public static <T> Predicate<T> alwaysFalsePredicate()
	{
		return t -> Boolean.FALSE.booleanValue();
	}
}
