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
    
}
