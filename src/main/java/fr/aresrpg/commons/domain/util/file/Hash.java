package fr.aresrpg.commons.domain.util.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.aresrpg.commons.domain.io.IO;
import fr.aresrpg.commons.domain.log.Logger;

public enum Hash {

	MD2("MD2"),
	MD5("MD5"),
	SHA1("SHA1"),
	SHA256("SHA-256"),
	SHA384("SHA-384"),
	SHA512("SHA-512");

	private String name;

	Hash(String name) {
		this.name = name;
	}

	/**
	 * Get the name of this hash algorithm
	 * @return the name of the hash algorithm
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the checksum of this byte array
	 *
	 * @param bytes the bytes to get the checksum
	 * @return the checksum calculated
	 * @throws NoSuchAlgorithmException if the Algorithm is not available
	 */
	public byte[] checksum(byte[] bytes) throws NoSuchAlgorithmException {
		return MessageDigest.getInstance(getName()).digest(bytes);
	}

	/**
	 * Get the checksum of this InputStream
	 * @param in the InputStream to get the checksum
	 * @return the checksum calculated
	 * @throws IOException if an io error occur
	 * @throws NoSuchAlgorithmException if the Algorithm is not available
	 */
	public byte[] checksum(InputStream in) throws IOException, NoSuchAlgorithmException {
		return MessageDigest.getInstance(getName()).digest(IO.toByteArray(in));
	}

	/**
	 * Get the checksum of this File
	 * @param input the File to get the checksum
	 * @return the checksum calculated
	 * @throws IOException if an io error occur
	 * @throws NoSuchAlgorithmException if the Algorithm is not available
	 */
	public byte[] checksum(File input) throws IOException, NoSuchAlgorithmException {
		return checksum(new FileInputStream(input));
	}

}