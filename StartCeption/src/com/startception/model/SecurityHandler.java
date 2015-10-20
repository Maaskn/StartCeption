package com.startception.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityHandler {
	private static final String SALT = "sla0418sXL";

	public static String toHashText(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		text = text + SALT;

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(text.getBytes("UTF-8"));
		byte[] hash = md.digest();
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < hash.length; i++) {
			hexString.append(Integer.toHexString(0xFF & hash[i]));
		}

		//System.out.println(hexString.toString());

		return hexString.toString();

	}

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
