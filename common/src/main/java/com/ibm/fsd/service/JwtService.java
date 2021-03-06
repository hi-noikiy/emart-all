package com.ibm.fsd.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Tool class generated by jwt token
 */
@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Value("${jwt.expiration}")
	private Long expiration;

	@Autowired
	AuthenticationManager authenticationManager;

	/**
	 * JWT Authentication
	 * 
	 * @param username
	 * @param password
	 * @return UserDetails
	 * @throws AuthenticationException
	 */
	public UserDetails authenticate(String username, String password) throws AuthenticationException {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return (UserDetails) authentication.getPrincipal();
	}

	/**
	 * generate token
	 * 
	 * @param userDetails
	 * @return
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(expirationDate())
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	/**
	 * check token
	 * 
	 * @param token
	 * @param userDetails
	 * @return boolean
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	/**
	 * token expired
	 * 
	 * @param token
	 * @return boolean
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = extractExpiration(token);
		return expiration.before(new Date());
	}

	/**
	 * Expiration time of token generation
	 * 
	 * @return date
	 */
	private Date expirationDate() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

}
