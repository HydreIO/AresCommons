package fr.aresrpg.commons.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.aresrpg.commons.log.Logger;

public enum Hash {

	MD5("MD5"),
	SHA1("SHA1"),
	SHA256("SHA-256"),
	SHA512("SHA-512");

	private String name;

	Hash(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public byte[] checksum(File input) {
		try (InputStream in = new FileInputStream(input)) {
			MessageDigest digest = MessageDigest.getInstance(getName());
			byte[] block = new byte[4096];
			int length;
			while ((length = in.read(block)) > 0)
				digest.update(block, 0, length);
			return digest.digest();
		} catch (Exception e) {
			Logger.trace(e);
		}
		return new byte[0];
	}

	public String toString(File input) {
		ByteBuffer buffer = ByteBuffer.wrap(checksum(input));
		return IntStream.generate(buffer::get).limit(buffer.remaining()).mapToObj(String::valueOf).collect(Collectors.joining());
	}

	public String toString(Supplier<File> supplier) {
		ByteBuffer buffer = ByteBuffer.wrap(checksum(supplier.get()));
		return IntStream.generate(buffer::get).limit(buffer.remaining()).mapToObj(String::valueOf).collect(Collectors.joining());
	}

}