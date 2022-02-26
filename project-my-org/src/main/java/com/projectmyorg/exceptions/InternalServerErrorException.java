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
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public InternalServerErrorException() {
		super();
	}

	/**
	 * @param message
	 */
	public InternalServerErrorException(String message) {
		super(message);
	}

}
