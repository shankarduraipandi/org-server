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
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public AuthException(String message) {
        super(message);
    }

}
