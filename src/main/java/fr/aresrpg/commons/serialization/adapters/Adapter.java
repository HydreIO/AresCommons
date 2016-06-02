package fr.aresrpg.commons.serialization.adapters;

import fr.aresrpg.commons.reflection.ParametrizedClass;

public interface Adapter<I , O> {
	O adaptTo(I in);
	I adaptFrom(O out);
	ParametrizedClass<I> getInType();
	ParametrizedClass<O> getOutType();
}
