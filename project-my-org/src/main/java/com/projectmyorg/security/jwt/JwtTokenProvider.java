package com.projectmyorg.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.projectmyorg.commons.Constants;
import com.projectmyorg.commons.ResponseMessages;
import com.projectmyorg.domain.AuthUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Shankar D
 *
 */
@Component
public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

	@Value("${projectmyorg.app.jwtSecret}")
	private String jwtSecret;
    
	@Value("${projectmyorg.app.jwtExpirationMs}")
	private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
		AuthUserDetails userPrincipal = (AuthUserDetails) authentication.getPrincipal();
		List<String> authorities = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		
		Map<String, Object> claims = new HashMap<>();
		claims.put(Constants.AUTHORITIES, authorities);
		claims.put(Constants.USER_NAME, userPrincipal.getUsername());
		claims.put(Constants.UUID, userPrincipal.getUuid());
		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
    
    public boolean validateJwtToken(HttpServletRequest request, String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (ExpiredJwtException e) {
			LOGGER.error("JWT token is expired: {}", e.getMessage());
			request.setAttribute(Constants.EXPIRED, ResponseMessages.JWT_EXPIRED);
		} catch (Exception e) {
			LOGGER.error("Invalid JWT signature: {}", e.getMessage());
			request.setAttribute(Constants.INVALID, ResponseMessages.INVALID_JWT);
		}
		return false;
	}
    
    private Map<String, Object> getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}

	public String getUserNameFromJwtToken(String token) {
		Map<String, Object> claims = getAllClaimsFromToken(token);
		if (claims.get(Constants.USER_NAME) != null)
			return claims.get(Constants.USER_NAME).toString();

		return null;
	}

}
