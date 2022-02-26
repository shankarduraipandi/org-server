/**
 * 
 */
package com.projectmyorg.dto.request;

import javax.validation.constraints.NotBlank;

/**
 * @author Shankar D
 *
 */
public class EmpTypeDto {
	
	@NotBlank(message = "EmpType should not be null or empty")
	private String empType;
	

	/**
	 * 
	 */
	public EmpTypeDto() {
		super();
	}

	/**
	 * @param empType
	 */
	public EmpTypeDto(@NotBlank(message = "EmpType should not be null or empty") String empType) {
		super();
		this.empType = empType;
	}

	/**
	 * @return the empType
	 */
	public String getEmpType() {
		return empType;
	}

	/**
	 * @param empType the empType to set
	 */
	public void setEmpType(String empType) {
		this.empType = empType;
	}

}
