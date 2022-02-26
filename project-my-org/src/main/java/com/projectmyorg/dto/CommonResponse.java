/**
 * 
 */
package com.projectmyorg.dto;

import com.projectmyorg.dto.response.BaseResponse;

/**
 * @author Shankar D
 *
 */
public class CommonResponse extends BaseResponse {
	
	private Object data;
	
	/**
	 * 
	 */
	public CommonResponse() {
		super();
	}

	/**
	 * @param data
	 */
	public CommonResponse(Object data) {
		super();
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
}
