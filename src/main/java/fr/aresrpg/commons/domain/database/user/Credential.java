package fr.aresrpg.commons.domain.database.user;

/**
 * usage : <blockquote>
 * 
 * <pre>
 * Credential cred = () -&gt; &quot;User:Pass@Host:Port&quot;;
 * </pre>
 * 
 * </blockquote>
 * 
 * @author MrSceat
 */

@FunctionalInterface
public interface Credential {

	String get();

	default String getUser() {
		return get().split(":")[0];
	}

	default String getPass() {
		return get().split("@")[0].split(":")[1];
	}

	default String getHostAdress() {
		String value = get();
		return value.substring(value.indexOf('@') + 1).split(":")[0];
	}

	default int getPort() {
		String value = get();
		return Integer.parseInt(value.split(":")[2]);
	}
}
