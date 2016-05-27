package fr.aresrpg.commons.i18n;

import static org.objectweb.asm.Opcodes.AALOAD;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ACC_VARARGS;
import static org.objectweb.asm.Opcodes.ACONST_NULL;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.ICONST_1;
import static org.objectweb.asm.Opcodes.ICONST_2;
import static org.objectweb.asm.Opcodes.ICONST_3;
import static org.objectweb.asm.Opcodes.ICONST_4;
import static org.objectweb.asm.Opcodes.ICONST_5;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.NEW;
import static org.objectweb.asm.Opcodes.RETURN;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import fr.aresrpg.commons.i18n.annotation.LangAnnotation;
import fr.aresrpg.commons.log.Logger;

public class ASMPropertiesI18N implements I18N {
	public class ByteClassLoader extends ClassLoader {
		public ByteClassLoader(ClassLoader classLoader) {
			super(classLoader);
		}

		public ByteClassLoader() {
			super();
		}

		public Class<?> defineClass(String name, byte[] bytes) {
			return defineClass(name, bytes, 0, bytes.length, null);
		}
	}

	public static final File FOLDER = new File("lang");
	public static final String ENCODING = "UTF-8";
	public static final Pattern ARGS_PATTERN = Pattern.compile("\\{(\\d+)?\\}");
	public static final String STRING_BUILDER = "java/lang/StringBuilder";
	public static final String STRING_BUILDER_APPEND = "append";
	public static final String INIT = "<init>";
	@SuppressWarnings("rawtypes")
	private Set<Class> classes = new HashSet<>();
	private ByteClassLoader loader = new ByteClassLoader(ClassLoader.getSystemClassLoader());

	static {
		if (!FOLDER.exists() && !FOLDER.mkdir()) throw new IllegalStateException("Could'not create folder lang");
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends L10N> T createL10N(Locale locale, Class<T> clazz) {
		try {
			if (!classes.contains(clazz)) {
				storeDefault(clazz);
				classes.add(clazz);
			}
			String name = "fr.aresrpg.commons.i18n." + clazz.getName().replace('.', '_') + getName(locale) + "Impl";
			return (T) createOrLoad(name, locale, clazz).newInstance();
		} catch (Exception e) {
			Logger.MAIN_LOGGER.severe(e);
		}
		return null;
	}

	protected Class<?> createOrLoad(String name, Locale locale, Class<?> reference) throws Exception {
		try {
			return loader.loadClass(name);
		} catch (ClassNotFoundException e) {// NOSONAR Inform if class not found
			return loader.defineClass(name, createClass(name, findProperties(locale), reference));
		}
	}

	protected byte[] createClass(String name, Properties properties, Class<?> reference) throws Exception {
		ClassWriter cw = new ClassWriter(0);
		cw.visit(52, ACC_PUBLIC + ACC_SUPER, name.replace('.', '/'), null, "java/lang/Object", new String[] { Type.getInternalName(reference) });
		// Constructor
		MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, INIT, "()V", null, null);
		mv.visitCode();
		Label l0 = new Label();
		mv.visitLabel(l0);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", INIT, "()V", false);
		mv.visitInsn(RETURN);
		Label l1 = new Label();
		mv.visitLabel(l1);
		mv.visitMaxs(1, 1);
		mv.visitEnd();
		for (Method method : reference.getMethods()) {
			String value = properties.getProperty(method.getName());
			if (value == null || value.isEmpty()) createNullMethod(cw, method);
			else createMethod(cw, method, value);

		}
		return cw.toByteArray();
	}

	private void createNullMethod(ClassWriter cw, Method reference) {
		MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_VARARGS, reference.getName(), methodSignature(reference), null, null);
		mv.visitCode();
		Label l0 = new Label();
		mv.visitLabel(l0);
		mv.visitLineNumber(16, l0);
		mv.visitInsn(ACONST_NULL);
		mv.visitInsn(ARETURN);
		Label l1 = new Label();
		mv.visitLabel(l1);
		mv.visitMaxs(1, reference.getParameterCount() + 1);// +1 for this
		mv.visitEnd();
	}

	private void createMethod(ClassWriter cw, Method reference, String value) {
		MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_VARARGS, reference.getName(), methodSignature(reference), null, null);
		mv.visitCode();
		Label l0 = new Label();
		mv.visitLabel(l0);
		mv.visitTypeInsn(NEW, STRING_BUILDER);
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESPECIAL, STRING_BUILDER, INIT, "()V", false);
		Matcher matcher = ARGS_PATTERN.matcher(value);
		int index = 0;
		while (true) {
			if (!matcher.find()) {
				mv.visitLdcInsn(value.substring(index));
				mv.visitMethodInsn(INVOKEVIRTUAL, STRING_BUILDER, STRING_BUILDER_APPEND, "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
				mv.visitMethodInsn(INVOKEVIRTUAL, STRING_BUILDER, "toString", "()Ljava/lang/String;", false);
				mv.visitInsn(ARETURN);
				Label l1 = new Label();
				mv.visitLabel(l1);
				mv.visitMaxs(3, reference.getParameterCount() + 1);// +1 for this
				mv.visitEnd();
				return;
			}
			mv.visitLdcInsn(value.substring(index, matcher.start()));
			mv.visitMethodInsn(INVOKEVIRTUAL, STRING_BUILDER, STRING_BUILDER_APPEND, "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
			mv.visitVarInsn(ALOAD, 1);
			writeIntValue(mv, Integer.parseInt(matcher.group(1)));
			mv.visitInsn(AALOAD);
			mv.visitMethodInsn(INVOKEVIRTUAL, STRING_BUILDER, STRING_BUILDER_APPEND, "(Ljava/lang/Object;)Ljava/lang/StringBuilder;", false);
			index = matcher.end();
		}
	}

	private void writeIntValue(MethodVisitor mv, int value) {
		switch (value) {
			case 0:
				mv.visitInsn(ICONST_0);
				break;
			case 1:
				mv.visitInsn(ICONST_1);
				break;
			case 2:
				mv.visitInsn(ICONST_2);
				break;
			case 3:
				mv.visitInsn(ICONST_3);
				break;
			case 4:
				mv.visitInsn(ICONST_4);
				break;
			case 5:
				mv.visitInsn(ICONST_5);
				break;
			default:
				mv.visitVarInsn(BIPUSH, value);
				break;
		}
	}

	private String methodSignature(Method method) {
		StringBuilder sb = new StringBuilder().append('(');
		for (Type type : Type.getArgumentTypes(method))
			sb.append(type.getDescriptor());
		return sb.append(')').append(Type.getReturnType(method).getDescriptor()).toString();
	}

	public void storeDefault(Class<?> clazz) throws IOException {
		Map<Locale, Properties> properties = new HashMap<>();
		for (Method method : clazz.getMethods()) {
			for (Annotation annotation : method.getAnnotations()) {
				if (annotation.annotationType().getAnnotation(LangAnnotation.class) != null) {
					LangAnnotation l = annotation.annotationType().getAnnotation(LangAnnotation.class);
					Locale locale = new Locale(l.language(), l.country(), l.variant());
					Properties prop;
					if (!properties.containsKey(locale)) {
						prop = findOrCreate(locale);
						properties.put(locale, prop);
					} else prop = properties.get(locale);
					if (!prop.containsKey(method.getName())) prop.put(method.getName(), getValue(annotation));
				}
			}

		}
		for (Map.Entry<Locale, Properties> entry : properties.entrySet()) {
			save(entry.getValue(), new FileOutputStream(new File(FOLDER, getName(entry.getKey())) + ".lang"), entry.getKey().toString());
		}
	}

	public Properties findProperties(Locale locale) throws IOException {
		for (int i = 0; i < 3; i++) {
			Properties properties;
			if ((properties = tryGetProperties(locale.getLanguage() + (i < 2 ? "-" + locale.getCountry() : "") + (i < 1 ? "-" + locale.getVariant() : ""))) != null) return properties;
		}
		return null;
	}

	public Properties tryGetProperties(String name) throws IOException {
		File file = new File(FOLDER, name + ".lang");
		if (!file.exists()) return null;
		Properties properties = new Properties();
		properties.load(new FileReader(file));
		return properties;
	}

	public Properties findOrCreate(Locale locale) throws IOException {
		Properties p = findProperties(locale);
		return p == null ? new Properties() : p;
	}

	private String getName(Locale locale) {
		return locale.getLanguage() + (locale.getCountry() == null ? "" : locale.getCountry()) + (locale.getVariant() == null ? "" : locale.getVariant());
	}

	private String getValue(Annotation annotation) {
		try {
			return (String) annotation.getClass().getMethod("value").invoke(annotation);
		} catch (ReflectiveOperationException e) {
			throw new IllegalStateException("Annotation don't have value() method", e);
		}
	}

	private static void save(Properties properties, OutputStream outputStream, String comment) throws IOException {
		outputStream.write('#');
		outputStream.write(comment.getBytes(ENCODING));
		outputStream.write('\n');
		for (String name : properties.stringPropertyNames()) {
			outputStream.write(name.getBytes(ENCODING));
			outputStream.write('=');
			outputStream.write(properties.getProperty(name).replace("\n", "\\n").getBytes(ENCODING));
			outputStream.write('\n');
		}
		outputStream.flush();
		outputStream.close();
	}
}
