/**
 * 
 */
package com.projectmyorg.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author Shankar D
 *
 */
public class SingleStrRequestDto {
	
	@NotBlank(message = "Value should not be null or empty")
	private String value;

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
