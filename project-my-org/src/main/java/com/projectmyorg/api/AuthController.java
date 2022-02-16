/**
 * 
 */
package com.projectmyorg.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmyorg.commons.Constants;
import com.projectmyorg.commons.ResponseMessages;
import com.projectmyorg.dto.request.LoginRequest;
import com.projectmyorg.dto.response.JwtAuthenticationResponse;
import com.projectmyorg.security.jwt.JwtTokenProvider;

/**
 * @author Shankar D
 *
 */
@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	private static final String API_PREFIX = "/";
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    

	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
			HttpServletResponse response) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateJwtToken(authentication);
		
		return ResponseEntity.ok(new JwtAuthenticationResponse(true, ResponseMessages.SUCCESS, jwt));
	}
    
	public void setResponseCookie(String name, Object value, HttpServletResponse response, Integer maxAge) {
		Cookie cookie = new Cookie(name, (value != null) ? value.toString() : Constants.BLANK);
		cookie.setMaxAge(maxAge);
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setPath(API_PREFIX);
		response.addCookie(cookie);
	}
	
}
