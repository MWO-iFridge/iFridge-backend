package mwo.auth;

import java.io.Serializable;
import java.security.Key;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import mwo.entity.User;

@Component
public class TokenHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Value("${secret}")
	private String secret;
	
	private Key getKey(){
		String base64Key = DatatypeConverter.printBase64Binary(secret.getBytes());
		byte[] secretBytes = DatatypeConverter.parseBase64Binary(base64Key);
		return Keys.hmacShaKeyFor(secretBytes);
	}
	
	public String getUsernameFromToken(String token) throws Exception {
		Jws<Claims> claims;
	    claims = Jwts.parser()        
	    .setSigningKey(getKey())        
	    .parseClaimsJws(token);
		
	    String subject = claims.getBody().getSubject();
	    return subject;
	}
	
	public String generateToken(User user) {
		String jws = Jwts.builder()
			    .setSubject(user.getUsername())   
			    .signWith(getKey())      
			    .compact();   
		return jws;
	}
	

}
