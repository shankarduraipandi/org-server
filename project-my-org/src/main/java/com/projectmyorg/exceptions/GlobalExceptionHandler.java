package com.projectmyorg.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.projectmyorg.commons.ResponseMessages;
import com.projectmyorg.dto.response.ErrorResponse;

/**
 * @author Shankar D
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String errorMessage = error.getDefaultMessage();
			details.add(errorMessage);
		});

		ErrorResponse error = new ErrorResponse(false, ResponseMessages.BAD_REQUEST_MGS, details);
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(AuthException.class)
	public ResponseEntity<ErrorResponse> handleAuthException(AuthException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(false, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex,
			WebRequest request) {
		ErrorResponse error = new ErrorResponse(false, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
