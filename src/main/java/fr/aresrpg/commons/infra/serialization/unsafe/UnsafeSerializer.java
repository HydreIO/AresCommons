package fr.aresrpg.commons.infra.serialization.unsafe;

import fr.aresrpg.commons.domain.functional.consumer.BiConsumer;
import fr.aresrpg.commons.domain.functional.function.Function;
import fr.aresrpg.commons.domain.reflection.ParametrizedClass;
import fr.aresrpg.commons.domain.reflection.Reflection;
import fr.aresrpg.commons.domain.serialization.Format;
import fr.aresrpg.commons.domain.serialization.SerializationContext;
import fr.aresrpg.commons.domain.serialization.Serializer;
import fr.aresrpg.commons.domain.serialization.adapters.Adapter;
import fr.aresrpg.commons.domain.serialization.annotations.SerializedName;
import fr.aresrpg.commons.domain.serialization.factory.SerializationFactory;
import fr.aresrpg.commons.domain.types.TypeEnum;
import fr.aresrpg.commons.domain.unsafe.UnsafeAccessor;
import fr.aresrpg.commons.infra.serialization.BasicSerializationContext;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class UnsafeSerializer<T> implements Serializer<T> {
	private static final Unsafe UNSAFE = UnsafeAccessor.getUnsafe();
	private final String[] names;
	private final Function<T , Object>[] getters;
	private final TypeEnum[] types; //For serialization

	private final BiConsumer<T , Object>[] setters;
	private final Class[] classes; //For deserialization
	private final SerializationContext context;
	private final Class<T> clazz;
	private final SerializationFactory factory;

	public UnsafeSerializer(Class<T> clazz , SerializationFactory factory) {
		Field[] fields = Reflection.isInnerClass(clazz) ?
				Arrays.copyOfRange(clazz.getFields() , 1 , clazz.getDeclaredFields().length-2) : //Remove first field
				clazz.getDeclaredFields();
		fields = Arrays.stream(fields).filter(f -> !Modifier.isStatic(f.getModifiers()) &&
				!Modifier.isTransient(f.getModifiers())).toArray(Field[]::new);
		names = new String[fields.length];
		getters = new Function[fields.length];
		setters = new BiConsumer[fields.length];
		types = new TypeEnum[fields.length];
		classes = new Class[fields.length];
		context = new BasicSerializationContext(factory);
		init(fields , factory);
		this.clazz = clazz;
		this.factory = factory;
	}

	@Override
	public <O> void serialize(O output, T object, Format<?, O> format) throws IOException {
		TypeEnum type = TypeEnum.getType(object);
		format.writeBegin(output);
		if (type == TypeEnum.OBJECT) {
			format.writeBeginObject(output);
			for(int i = 0 ; i < names.length ; i++){
				Object value = getters[i].apply(object);
				format.writeValue(output, names[i], value == null ? TypeEnum.NULL : types[i], value, context);
				format.writeFieldSeparator(output, i == 0, i == names.length - 1);
			}
			format.writeEndObject(output);
		} else format.writeValue(output, null, type, object, context);
		format.writeEnd(output);
	}

	@Override
	public <I> void deserialize(I input, T object, Format<I, ?> format) throws IOException {
		Map<String , Object> map = new HashMap<>();
		format.read(input , map , context);
		deserialize(map , object);
	}

	@Override
	public <I> T deserialize(I input, Format<I, ?> format) throws IOException {
		try {
			T object = (T) UNSAFE.allocateInstance(clazz);
			deserialize(input , object , format);
			return object;
		} catch (InstantiationException e) {
			throw new IOException(e);
		}
	}


	@Override
	public T deserialize(Map<String, Object> values) throws IOException {
		try {
			T object = (T) UNSAFE.allocateInstance(clazz);
			deserialize(values , object);
			return object;
		} catch (InstantiationException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void deserialize(Map<String, Object> values, T object) throws IOException {
		for(int i = 0 ; i < names.length ; i++) {
			Object o = values.get(names[i]);
			if(o instanceof Map) {
				Class<?> c = classes[i];
				if(!Map.class.isAssignableFrom(c))
					o = factory.createOrGetSerializer(c).deserialize((Map)o);
			}
			setters[i].accept(object , o);
		}
	}


	private void init(Field[] fields , SerializationFactory factory){
		for(int i = 0 ; i < fields.length ; i++){
			Field field = fields[i];
			names[i] = factory.getFieldNamer().getName(field);
			Adapter[] chain = factory.getAdapterChain(new ParametrizedClass(field.getGenericType()));
			long offset = UNSAFE.objectFieldOffset(field);
			boolean volatileField = Reflection.isVolatileClass(field.getType());

			switch (TypeEnum.getPrimitiveType(field.getType())){
				case BOOLEAN:
					getters[i] = generateBooleanGetter(chain , offset , volatileField);
					setters[i] = generateBooleanSetter(chain , offset , volatileField);
					break;
				case BYTE:
					getters[i] = generateByteGetter(chain , offset , volatileField);
					setters[i] = generateByteSetter(chain , offset , volatileField);
					break;
				case SHORT:
					getters[i] = generateShortGetter(chain , offset , volatileField);
					setters[i] = generateShortSetter(chain , offset , volatileField);
					break;
				case CHAR:
					getters[i] = generateCharGetter(chain , offset , volatileField);
					setters[i] = generateCharSetter(chain , offset , volatileField);
					break;
				case INT:
					getters[i] = generateIntGetter(chain , offset , volatileField);
					setters[i] = generateIntSetter(chain , offset , volatileField);
					break;
				case LONG:
					getters[i] = generateLongGetter(chain , offset , volatileField);
					setters[i] = generateLongSetter(chain , offset , volatileField);
					break;
				case FLOAT:
					getters[i] = generateFloatGetter(chain , offset , volatileField);
					setters[i] = generateFloatSetter(chain , offset , volatileField);
					break;
				case DOUBLE:
					getters[i] = generateDoubleGetter(chain , offset , volatileField);
					setters[i] = generateDoubleSetter(chain , offset , volatileField);
					break;
				case OBJECT:
					getters[i] = generateObjectGetter(chain , offset , volatileField);
					setters[i] = generateObjectSetter(chain , offset , volatileField);
					break;
			}
			if(chain.length == 0){
				classes[i] = field.getType();
				types[i] = TypeEnum.getType(field.getType());
			}else {
				classes[i] = chain[chain.length -2].getInType().getRaw();
				types[i] = TypeEnum.getType(chain[chain.length -2].getOutType().getRaw());
			}

		}
	}

	private static <T> BiConsumer<T, Object> generateBooleanSetter(Adapter[] chain, long offset, boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? (i , v) -> UNSAFE.putBooleanVolatile(i , offset , (Boolean)v) :
					(i , v) -> UNSAFE.putBoolean(i , offset , (Boolean)v);
		}else {
			if(volatileField){
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putBooleanVolatile(inst , offset , (Boolean)v);
				};
			}else {
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putBoolean(inst , offset , (Boolean)v);
				};
			}
		}
	}

	private static <T> BiConsumer<T, Object> generateByteSetter(Adapter[] chain, long offset, boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? (i , v) -> UNSAFE.putByteVolatile(i , offset , (Byte)v) :
					(i , v) -> UNSAFE.putByte(i , offset , (Byte)v);
		}else {
			if(volatileField){
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putByteVolatile(inst , offset , (Byte)v);
				};
			}else {
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putByte(inst , offset , (Byte)v);
				};
			}
		}
	}

	private static <T> BiConsumer<T, Object> generateShortSetter(Adapter[] chain, long offset, boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? (i , v) -> UNSAFE.putShortVolatile(i , offset , (Short)v) :
					(i , v) -> UNSAFE.putShort(i , offset , (Short)v);
		}else {
			if(volatileField){
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putShortVolatile(inst , offset , (Short)v);
				};
			}else {
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putShort(inst , offset , (Short)v);
				};
			}
		}
	}

	private static <T> BiConsumer<T, Object> generateCharSetter(Adapter[] chain, long offset, boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? (i , v) -> UNSAFE.putCharVolatile(i , offset , (Character)v) :
					(i , v) -> UNSAFE.putChar(i , offset , (Character)v);
		}else {
			if(volatileField){
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putCharVolatile(inst , offset , (Character)v);
				};
			}else {
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putChar(inst , offset , (Character)v);
				};
			}
		}
	}

	private static <T> BiConsumer<T, Object> generateIntSetter(Adapter[] chain, long offset, boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? (i , v) -> UNSAFE.putIntVolatile(i , offset , (Integer)v) :
					(i , v) -> UNSAFE.putInt(i , offset , (Integer)v);
		}else {
			if(volatileField){
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putIntVolatile(inst , offset , (Integer)v);
				};
			}else {
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putInt(inst , offset , (Integer)v);
				};
			}
		}
	}

	private static <T> BiConsumer<T, Object> generateLongSetter(Adapter[] chain, long offset, boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? (i , v) -> UNSAFE.putLongVolatile(i , offset , (Long)v) :
					(i , v) -> UNSAFE.putLong(i , offset , (Long)v);
		}else {
			if(volatileField){
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putLongVolatile(inst , offset , (Long)v);
				};
			}else {
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putLong(inst , offset , (Long)v);
				};
			}
		}
	}

	private static <T> BiConsumer<T, Object> generateFloatSetter(Adapter[] chain, long offset, boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? (i , v) -> UNSAFE.putFloatVolatile(i , offset , (Float)v) :
					(i , v) -> UNSAFE.putFloat(i , offset , (Float)v);
		}else {
			if(volatileField){
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putFloatVolatile(inst , offset , (Float)v);
				};
			}else {
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putFloat(inst , offset , (Float)v);
				};
			}
		}
	}

	private static <T> BiConsumer<T, Object> generateDoubleSetter(Adapter[] chain, long offset, boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? (i , v) -> UNSAFE.putDoubleVolatile(i , offset , (Double)v) :
					(i , v) -> UNSAFE.putDouble(i , offset , (Double)v);
		}else {
			if(volatileField){
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putDoubleVolatile(inst , offset , (Double)v);
				};
			}else {
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putDouble(inst , offset , (Double)v);
				};
			}
		}
	}

	private static <T> BiConsumer<T, Object> generateObjectSetter(Adapter[] chain, long offset, boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? (i , v) -> UNSAFE.putObjectVolatile(i , offset , v) :
					(i , v) -> UNSAFE.putObject(i , offset , v);
		}else {
			if(volatileField){
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putObjectVolatile(inst , offset , v);
				};
			}else {
				return (inst , v) -> {
					for (int i = chain.length; i >= 0; i--)
						v = chain[i].adaptFrom(v);
					UNSAFE.putObject(inst , offset , v);
				};
			}
		}
	}

	private static <T> Function<T , Object> generateBooleanGetter(Adapter[] chain, long offset , boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? i -> UNSAFE.getBooleanVolatile(i , offset) :
					i -> UNSAFE.getBoolean(i , offset);
		}else {
			if(volatileField){
				return i -> {
					Object o = UNSAFE.getBooleanVolatile(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}else {
				return i -> {
					Object o = UNSAFE.getBoolean(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}
		}
	}

	private static <T> Function<T , Object> generateByteGetter(Adapter[] chain, long offset , boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? i -> UNSAFE.getByteVolatile(i , offset) :
					i -> UNSAFE.getByte(i , offset);
		}else {
			if(volatileField){
				return i -> {
					Object o = UNSAFE.getByteVolatile(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}else {
				return i -> {
					Object o = UNSAFE.getByte(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}
		}
	}

	private static <T> Function<T , Object> generateShortGetter(Adapter[] chain, long offset , boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? i -> UNSAFE.getShortVolatile(i , offset) :
					i -> UNSAFE.getShort(i , offset);
		}else {
			if(volatileField){
				return i -> {
					Object o = UNSAFE.getShortVolatile(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}else {
				return i -> {
					Object o = UNSAFE.getShort(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}
		}
	}

	private static <T> Function<T , Object> generateCharGetter(Adapter[] chain, long offset , boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? i -> UNSAFE.getCharVolatile(i , offset) :
					i -> UNSAFE.getChar(i , offset);
		}else {
			if(volatileField){
				return i -> {
					Object o = UNSAFE.getCharVolatile(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}else {
				return i -> {
					Object o = UNSAFE.getChar(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}
		}
	}

	private static <T> Function<T , Object> generateIntGetter(Adapter[] chain, long offset , boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? i -> UNSAFE.getIntVolatile(i , offset) :
					i -> UNSAFE.getInt(i , offset);
		}else {
			if(volatileField){
				return i -> {
					Object o = UNSAFE.getIntVolatile(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}else {
				return i -> {
					Object o = UNSAFE.getInt(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}
		}
	}

	private static <T> Function<T , Object> generateLongGetter(Adapter[] chain, long offset , boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? i -> UNSAFE.getLongVolatile(i , offset) :
					i -> UNSAFE.getLong(i , offset);
		}else {
			if(volatileField){
				return i -> {
					Object o = UNSAFE.getLongVolatile(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}else {
				return i -> {
					Object o = UNSAFE.getLong(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}
		}
	}

	private static <T> Function<T , Object> generateFloatGetter(Adapter[] chain, long offset , boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? i -> UNSAFE.getFloatVolatile(i , offset) :
					i -> UNSAFE.getFloat(i , offset);
		}else {
			if(volatileField){
				return i -> {
					Object o = UNSAFE.getFloatVolatile(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}else {
				return i -> {
					Object o = UNSAFE.getFloat(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}
		}
	}

	private static <T> Function<T , Object> generateDoubleGetter(Adapter[] chain, long offset , boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? i -> UNSAFE.getDoubleVolatile(i , offset) :
					i -> UNSAFE.getDouble(i , offset);
		}else {
			if(volatileField){
				return i -> {
					Object o = UNSAFE.getDoubleVolatile(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}else {
				return i -> {
					Object o = UNSAFE.getDouble(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}
		}
	}

	private static <T> Function<T , Object> generateObjectGetter(Adapter[] chain, long offset , boolean volatileField) {
		if(chain.length == 0){
			return volatileField ? i -> UNSAFE.getObjectVolatile(i , offset) :
					i -> UNSAFE.getObject(i , offset);
		}else {
			if(volatileField){
				return i -> {
					Object o = UNSAFE.getObjectVolatile(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}else {
				return i -> {
					Object o = UNSAFE.getObject(i , offset);
					for (Adapter adapter : chain)
						o = adapter.adaptTo(o);
					return o;
				};
			}
		}
	}

}