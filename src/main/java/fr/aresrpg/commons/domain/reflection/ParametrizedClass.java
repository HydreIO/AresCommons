package fr.aresrpg.commons.domain.reflection;

import fr.aresrpg.commons.domain.Value;
import fr.aresrpg.commons.domain.util.Iterators;
import fr.aresrpg.commons.domain.util.Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Objects;

/**
 * A class with type parameters
 * @param <T> the class
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 */
public class ParametrizedClass<T> implements Value<Class<T>> {
	private final Type type;
	private final Class<T> raw;

	/**
	 * Create a new Parametrized class
	 */
	protected ParametrizedClass() {
		if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
			this.type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			this.raw = Types.getClazz(type);
		} else
			throw new IllegalStateException("Cannot get superclass type parameters");

	}

	/**
	 * Create a new ParametrizedClass with the specifier type
	 * @param type the type represented by the parameterized class
	 */
	public ParametrizedClass(Type type) {
		this.type = type;
		this.raw = Types.getClazz(type);
	}

	/**
	 * Get the type hold by this parameterized class
	 * @return the type
	 */
	public final Type getType() {
		return this.type;
	}

	/**
	 * Get the raw class hold by this parameterized class
	 * 
	 * @return the raw class
	 */
	public final Class<T> getRaw(){
		return raw;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || !(o instanceof ParametrizedClass)) return false;
		ParametrizedClass<?> that = (ParametrizedClass<?>) o;
		return Objects.equals(raw, that.raw);//Fix that
	}

	@Override
	public int hashCode() {
		return Objects.hash(raw);
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Iterator<Class<T>> iterator() {
		return Iterators.of(getRaw());
	}

}
