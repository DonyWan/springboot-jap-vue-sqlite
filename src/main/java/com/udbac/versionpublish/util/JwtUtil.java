package com.udbac.versionpublish.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
//	private final static String APP_ID = "app_id";
//	private final static String APP_SECRET = "app_secret";
	private final static long ISSUER = 60 * 60 * 1000;
	/**********************/
	private static final String KEY1 = "somethinghereshouldbelongdsaomfomdsaofmoosdmasmsm";

	/**
	 * 由字符串生成加密key
	 * 
	 * @return
	 */
	private static SecretKey generalKey() {
		SecretKey key = Keys.hmacShaKeyFor(KEY1.getBytes());
		return key;
	}

	// 创建token
	public static String createJWT(String subject) {
		Key key = generalKey();

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		Date expirationDate = new Date(ISSUER + nowMillis);
		// Let's set the JWT Claims
        String jws = Jwts.builder().setIssuedAt(now).setSubject(subject).setExpiration(expirationDate).signWith(key)
				.compact();

		return jws;
	}

	// 验证token
	public static Claims parseJWT(String jwt) {
		Key key = generalKey();
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
		} catch (JwtException e) {
			claims = null;
		}
		// System.out.println("ID: " + claims.getId());
		// System.out.println("Subject: " + claims.getSubject());
		// System.out.println("Issuer: " + claims.getIssuer());
		// System.out.println("Expiration: " + claims.getExpiration());
		return claims;
	}

	public static boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
	public static void main(String[] args) {
        System.out.println(createJWT("dun"));
    }
}
