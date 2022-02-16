package com.projectmyorg.security.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.projectmyorg.commons.Constants;
import com.projectmyorg.service.UserServiceImpl;

/**
 * @author Shankar D
 *
 */
public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/api/v1/auth/login")) {
			filterChain.doFilter(request, response); 
		} else {
			String jwt = parseJwt(request);
			if (jwt != null && tokenProvider.validateJwtToken(request, jwt)) {
				String username = tokenProvider.getUserNameFromJwtToken(jwt);
				UserDetails userDetails = userService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			filterChain.doFilter(request, response);
		}
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(Constants.TOKEN_TYPE + Constants.SPACE))
			return headerAuth.substring(7, headerAuth.length());
		
		return null;
	}

	public String parseJwtForCookie(HttpServletRequest request) {
		Optional<String> accessTokenCookie = readCookie(request, Constants.PMO_AUTH_TOKEN);
		if (!accessTokenCookie.isEmpty()) {
			return accessTokenCookie.get();
		}
		return null;
	}

	public Optional<String> readCookie(HttpServletRequest request, String key) {
		if (request.getCookies() != null) {
			return Arrays.stream(request.getCookies()).filter(c -> key.equals(c.getName())).map(Cookie::getValue)
					.findAny();
		} else {
			return Optional.empty();
		}
	}

}
