package fr.aresrpg.commons.i18n;

import fr.aresrpg.commons.i18n.annotation.LangAnnotation;
import fr.aresrpg.commons.i18n.test.L10NProxy;
import fr.aresrpg.commons.log.Logger;
import org.objectweb.asm.*;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

import static org.objectweb.asm.Opcodes.*;


public class ASMPropertiesI18N implements I18N{
	public static final File FOLDER = new File("lang");
	public static final String ENCODING = "UTF-8";
	public static final Set<Class> classes = new HashSet<>();

	static {
		if(!FOLDER.exists() && !FOLDER.mkdir())
			throw new IllegalStateException("Could'not create folder lang");
	}

	@Override
	public <T extends L10N> T createL10N(Locale locale, Class<T> clazz) {
		try {
			if(!classes.contains(clazz)){
				storeDefault(clazz);
				classes.add(clazz);
			}
			String name = clazz.getName().replace('.' , '$')+getName(locale)+"Impl";
			createClass(name , findProperties(locale) , clazz);
		}catch (Exception e){
			Logger.MAIN_LOGGER.severe(e);
		}
		return null;
	}

	public byte[] createClass(String name , Properties properties , Class<?> reference) throws ReflectiveOperationException , IOException{
		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;
		cw.visit(52, ACC_PUBLIC + ACC_SUPER, name, null, "java/lang/Object", new String[]{"fr/aresrpg/commons/i18n/test/L10NProxy"});

		cw.visitSource(name + ".java", null);
		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(3, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			mv.visitInsn(RETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", "L"+name+";", null, l0, l1, 0);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		for(Method method : reference.getMethods());
		return cw.toByteArray();
	}

	public void storeDefault(Class<?> clazz) throws IOException {
		Map<Locale , Properties> properties = new HashMap<>();
		for(Method method : clazz.getMethods()){
			for(Annotation annotation : method.getAnnotations()){
				if(annotation.annotationType().getAnnotation(LangAnnotation.class) != null){
					LangAnnotation l = annotation.annotationType().getAnnotation(LangAnnotation.class);
					Locale locale = new Locale(l.language() , l.country() , l.variant());
					Properties prop;
					if(!properties.containsKey(locale)){
						prop = findOrCreate(locale);
						properties.put(locale , prop);
					}else
						prop = properties.get(locale);
					if(!prop.containsKey(method.getName()))
						prop.put(method.getName() , getValue(annotation));
				}
			}

		}
		for(Map.Entry<Locale , Properties> entry : properties.entrySet()){
			save(entry.getValue() , new FileOutputStream(new File(FOLDER , getName(entry.getKey())) + ".lang") , entry.getKey().toString());
		}
	}

	public Properties findProperties(Locale locale) throws IOException {
		for(int i = 0 ; i < 3 ; i++){
			Properties properties;
			if((properties = tryGetProperties(locale.getLanguage() + (i < 2 ? "-" + locale.getCountry() : "")
																	+ (i < 1 ? "-" + locale.getVariant() : ""))) != null)
				return properties;
		}
		return null;
	}

	public Properties tryGetProperties(String name) throws IOException {
		File file = new File(FOLDER , name + ".lang");
		if(!file.exists())
			return null;
		Properties properties = new Properties();
		properties.load(new FileReader(file));
		return properties;
	}

	public Properties findOrCreate(Locale locale) throws IOException {
		Properties p = findProperties(locale);
		return p == null ? new Properties() : p;
	}

	private String getName(Locale locale){
		return locale.getLanguage() + (locale.getCountry() == null ? "" : locale.getCountry()) +
				(locale.getVariant() == null ? "" : locale.getVariant());
	}

	private String getValue(Annotation annotation){
		try {
			return (String) annotation.getClass().getMethod("value").invoke(annotation);
		} catch (ReflectiveOperationException e) {
			throw new IllegalStateException("Annotation don't have value() method" , e);
		}
	}

	private static void save(Properties properties , OutputStream outputStream , String comment) throws IOException {
		outputStream.write('#');
		outputStream.write(comment.getBytes(ENCODING));
		outputStream.write('\n');
		for(String name : properties.stringPropertyNames()){
			outputStream.write(name.getBytes(ENCODING));
			outputStream.write('=');
			outputStream.write(properties.getProperty(name).replace("\n" , "\\n").getBytes(ENCODING));
			outputStream.write('\n');
		}
		outputStream.flush();
		outputStream.close();
	}

	public static void main(String[] args) throws IOException {
		new ASMPropertiesI18N().storeDefault(L10NProxy.class);
	}
}
