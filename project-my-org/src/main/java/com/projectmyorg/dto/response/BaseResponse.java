/**
 * 
 */
package com.projectmyorg.dto.response;

import com.projectmyorg.commons.ResponseMessages;

/**
 * @author Shankar D
 *
 */
public class BaseResponse {

	private Boolean status;
	private String message;
	
	/**
	 * 
	 */
	public BaseResponse() {
		super();
		this.status = true;
		this.message = ResponseMessages.SUCCESS;
	}
	
	
	/**
	 * @param status
	 * @param message
	 */
	public BaseResponse(Boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}


	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	

}
