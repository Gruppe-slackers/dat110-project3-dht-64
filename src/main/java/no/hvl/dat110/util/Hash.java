package no.hvl.dat110.util;

/**
 * exercise/demo purpose in dat110
 * @author tdoy
 *
 */

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

	private static String algorithm = "MD5";

	public static void setAlgorithm(String algorithm) {
		Hash.algorithm = algorithm;
	}

	public static BigInteger hashOf(String entity) {
		 /* Task: Hash a given string using MD5 and return the result as a BigInteger.
		 we use MD5 with 128 bits digest
		 compute the hash of the input 'entity'
		 convert the hash into hex format
		 convert the hex into BigInteger
		 return the BigInteger`*/

		// completed: true;

		BigInteger hashint = null;
		try {
			// Get instance of MD5 message digest algorithm
			MessageDigest instance = MessageDigest.getInstance("MD5");
			// Compute the hash value of the input string
			byte[] hashBytes = instance.digest(entity.getBytes());
			// Convert byte array to BigInteger
            hashint = new BigInteger(1, hashBytes);

		} catch (NoSuchAlgorithmException e) {
			// Handle NoSuchAlgorithmException
			e.printStackTrace();
			return BigInteger.ZERO; // Or any other appropriate action
		}
		return hashint;
	}
	
	public static BigInteger addressSize() {
		/*Task: compute the address size of MD5
	 	* compute the number of bits = bitSize()
	 	* compute the address size = 2 ^ number of bits
	 	* return the address size */
		//completed: true;

		int addressSize = bitSize();
		//md5 is a 128-bit hash, thus the address size will be 2^128
		return BigInteger.valueOf(2).pow(addressSize);
	}

	public static int bitSize() {
		// Task: find the digest length
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			int digestlen = md.getDigestLength();
			return digestlen * 8; //konverterer fra bytes til bits
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}
