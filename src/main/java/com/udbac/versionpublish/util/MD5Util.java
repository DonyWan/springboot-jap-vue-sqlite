package com.udbac.versionpublish.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	private static final String MD5 = "md5";// 密钥

	public static String getMD5(String string) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance(MD5);
			byte[] bytes = string.getBytes();
			md.update(bytes);
			byte[] digest = md.digest();
			BigInteger big = new BigInteger(digest);
			result = big.toString(32);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(MD5Util.getMD5("dun"));
//		String password = "9htlsqhh1qdpslniegcj2fd7m";
//		if(MD5Util.getMD5("test").equals(password)) {
//			System.out.println("true");
//		}
	}
}
