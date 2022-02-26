/**
 * 
 */
package com.projectmyorg.dto.request;

import javax.validation.constraints.NotBlank;

/**
 * @author Shankar D
 *
 */
public class DeptDto {
	
	@NotBlank(message = "dept should not be null or empty")
	private String dept;
	
	/**
	 * 
	 */
	public DeptDto() {
		super();
	}

	/**
	 * @param dept
	 */
	public DeptDto(@NotBlank(message = "dept should not be null or empty") String dept) {
		super();
		this.dept = dept;
	}

	/**
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}

	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}

}
