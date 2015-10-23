package com.startception.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * This class handles hashing and salting of the strings that comes from the requests
 * @author Erik Perez
 * */
public class SecurityHandler {
	private static final String SALT = "sla0418sXL";
	/**
	 * This method appends the salt string to the text and then converts the result to a hash string.
	 * @param text the text to be appended and eventually converted to a hash string
	 * @return the converted hashed string
	 * @throws NoSuchAlgorithmException When a particular cryptographic algorithm is requested but not available
	 * @throws UnsupportedEncodingException The character encoding is not supported
	 * */
	public static String toHashText(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		text = text + SALT;

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(text.getBytes("UTF-8"));
		byte[] hash = md.digest();
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < hash.length; i++) {
			hexString.append(Integer.toHexString(0xFF & hash[i]));
		}


		return hexString.toString();

	}
	
	/**
	 * This method analyze if the characters in a string are allowed or not
	 * @param textToAnalize the text to be analized
	 * @param isEmail if the text to analize is an email or not
	 * @return true if the text to be analized is allowed, false otherwise
	 * */
	public static boolean analyzeCharacters(String textToAnalize, boolean isEmail) {
		
		if (isEmail) {
			boolean allowed = textToAnalize.matches("^[A-Za-z0-9\\.@]+$");// Character.isLetterOrDigit(c);
			if (!allowed)
				return false;			
		} else {
			boolean allowed = textToAnalize.matches("^[A-Za-z0-9]+$");
			if (!allowed)
				return false;			
		}
		return true;
	}

}
