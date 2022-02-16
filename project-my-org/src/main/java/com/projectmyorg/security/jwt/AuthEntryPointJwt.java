package com.projectmyorg.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectmyorg.commons.Constants;
import com.projectmyorg.dto.response.ErrorResponse;

/**
 * @author Shankar D
 *
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthEntryPointJwt.class);

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		String exceptionMessage;
		if (request.getAttribute(Constants.EXPIRED) != null)
			exceptionMessage = (String) request.getAttribute(Constants.EXPIRED);
		else if (request.getAttribute(Constants.INVALID) != null)
			exceptionMessage = (String) request.getAttribute(Constants.INVALID);
		else
			exceptionMessage = authException.getMessage();

		try (ServletServerHttpResponse res = new ServletServerHttpResponse(response);) {
			LOGGER.error("Unauthorized error: {}", exceptionMessage);
			res.setStatusCode(HttpStatus.UNAUTHORIZED);
			res.getServletResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			res.getBody().write(mapper.writeValueAsString(new ErrorResponse(false, exceptionMessage)).getBytes());
		}
	}

}
