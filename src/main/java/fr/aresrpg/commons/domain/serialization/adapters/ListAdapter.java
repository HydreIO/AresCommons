package fr.aresrpg.commons.domain.serialization.adapters;

import fr.aresrpg.commons.domain.reflection.ParametrizedClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListAdapter implements Adapter<List,Object[]> {
	public static final Adapter<List,Object[]> INSTANCE = new ListAdapter();

	public static final ParametrizedClass<Object[]> OUT = new ParametrizedClass<Object[]>() {};
	public static final ParametrizedClass<List> IN = new ParametrizedClass<List>() {};

	private ListAdapter() {}

	@Override
	public Object[] adaptTo(List in) {
		return in.toArray(new Object[in.size()]);
	}

	@Override
	public List adaptFrom(Object[] out) {
		return new ArrayList(Arrays.asList(out));
	}

	@Override
	public ParametrizedClass<List> getInType() {
		return IN;
	}

	@Override
	public ParametrizedClass<Object[]> getOutType() {
		return OUT;
	}
}
