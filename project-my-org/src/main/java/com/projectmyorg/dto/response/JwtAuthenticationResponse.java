/**
 * 
 */
package com.projectmyorg.dto.response;

import com.projectmyorg.commons.Constants;

/**
 * @author Shankar D
 *
 */
public class JwtAuthenticationResponse extends BaseResponse {
	
	private String accessToken;
    private String tokenType = Constants.TOKEN_TYPE;
    
    
    
	/**
	 * 
	 */
	public JwtAuthenticationResponse() {
		super();
	}

	/**
	 * @param status
	 * @param message
	 * @param accessToken
	 * @param tokenType
	 */
	public JwtAuthenticationResponse(Boolean status, String message, String accessToken) {
		super(status, message);
		this.accessToken = accessToken;
	}
	
	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}
	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return tokenType;
	}
	/**
	 * @param tokenType the tokenType to set
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
    
}
