package com.startception.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {
	private String SALT = "sla0418sXL";
	
	public String hashText(String text) {
		try {
			text = text + getSALT();
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(text.getBytes("UTF-8"));
			byte[] hash = md.digest();
			StringBuffer hexString = new StringBuffer();
			
			for(int i = 0; i < hash.length; i++) {
				hexString.append(Integer.toHexString(0xFF & hash[i]));
			}
			
			System.out.println(hexString.toString());
			
			return hexString.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	public String getSALT() {
		return SALT;
	}
}
