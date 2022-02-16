/**
 * 
 */
package com.projectmyorg.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectmyorg.dto.response.ErrorResponse;

/**
 * @author Shankar D
 *
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AccessDeniedException ex) throws IOException, ServletException {
		try (ServletServerHttpResponse res = new ServletServerHttpResponse(httpServletResponse);) {
			res.setStatusCode(HttpStatus.UNAUTHORIZED);
			res.getServletResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			res.getBody().write(mapper
					.writeValueAsString(new ErrorResponse(false, ex.getMessage())).getBytes());
		}
	}

}
