package fr.aresrpg.commons.domain.serialization.factory;

import fr.aresrpg.commons.domain.reflection.ParametrizedClass;
import fr.aresrpg.commons.domain.serialization.FieldNamer;
import fr.aresrpg.commons.domain.serialization.Serializer;
import fr.aresrpg.commons.domain.serialization.adapters.Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * An serialization factory that create {@link Serializer}
 * @author Duarte David  {@literal <deltaduartedavid@gmail.com>}
 */
public interface SerializationFactory {
	<T> Serializer<T> createSerializer(Class<T> clazz);
	<T> Serializer<T> createOrGetSerializer(Class<T> clazz);

	List<Adapter<? , ?>> getAdapters();
	void addAdapter(Adapter<? , ?> adapter);
	void removeAdapter(Adapter<? , ?> adapter);
	<T> Adapter<T , ?> getAdapter(ParametrizedClass<T> clazz);

	FieldNamer getFieldNamer();

	void setFieldNamer(FieldNamer namer);

	default Adapter[] getAdapterChain(ParametrizedClass clazz){
		Adapter last;
		List<Adapter> adapters = new ArrayList<>();
		while ((last = getAdapter(clazz)) != null) {
			adapters.add(last);
			clazz = last.getOutType();
		}
		return adapters.toArray(new Adapter[adapters.size()]);
	}
}
