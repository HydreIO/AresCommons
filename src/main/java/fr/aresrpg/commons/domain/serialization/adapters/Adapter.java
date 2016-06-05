package fr.aresrpg.commons.domain.serialization.adapters;

import fr.aresrpg.commons.domain.reflection.ParametrizedClass;

public interface Adapter<I , O> {
	O adaptTo(I in);
	I adaptFrom(O out);
	ParametrizedClass<I> getInType();
	ParametrizedClass<O> getOutType();
}
