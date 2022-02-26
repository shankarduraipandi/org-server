package com.projectmyorg.commons;

/**
 * @author Shankar D
 *
 */
public class ResponseMessages {

    private ResponseMessages() {
		throw new IllegalStateException("Constants class");
	}
    
    // Success messages ->
    public static final String SUCCESS = "SUCCESS";
  
    // Validation messages ->
    public static final String BAD_REQUEST_MGS = "BAD REQUEST";
    
    // Auth exception messages ->
    public static final String JWT_EXPIRED = "JWT token is expired";
    public static final String INVALID_JWT = "Invalid JWT token";
	public static final String INTERNAL_SERVER_ERROR_MSG = "The request was not completed due to an internal error on the server side. Please try again later.";
	public static final String RECORD_NOT_FOUND_MGS = "Not Found";
    
}
