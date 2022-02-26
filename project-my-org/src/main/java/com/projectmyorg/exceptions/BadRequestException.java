/**
 * 
 */
package com.projectmyorg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Shankar D
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public BadRequestException() {
		super();
	}

	/**
	 * @param message
	 */
	public BadRequestException(String message) {
		super(message);
	}

}
