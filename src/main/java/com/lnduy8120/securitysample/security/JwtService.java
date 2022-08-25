package com.lnduy8120.securitysample.security;

import java.security.Key;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final Long EXPIRED_TIME = 259200L;

	public String generateTokenFromUsername(String username) {
		return Jwts.builder().setSubject(username).setExpiration(new Date(new Date().getTime() + EXPIRED_TIME))
				.signWith(SECRET_KEY).compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException e) {
			System.err.println("Token is expired");
		} catch (JwtException e) {
			System.err.println(e.getStackTrace());
		}
		return false;
	}

	public String getUsernameFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
	}

	public String getTokenFromRequest(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.replace("Bearer ", "");
		}
		return null;
	}
}
