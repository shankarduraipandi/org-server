/**
 * 
 */
package com.projectmyorg.dto.request;

import javax.validation.constraints.NotBlank;

/**
 * @author Shankar D
 *
 */
public class JobRoleDto {

	@NotBlank(message = "jobRole should not be null or empty")
	private String jobRole;

	/**
	 * 
	 */
	public JobRoleDto() {
		super();
	}

	/**
	 * @param jobRole
	 */
	public JobRoleDto(@NotBlank(message = "jobRole should not be null or empty") String jobRole) {
		super();
		this.jobRole = jobRole;
	}

	/**
	 * @return the jobRole
	 */
	public String getJobRole() {
		return jobRole;
	}

	/**
	 * @param jobRole the jobRole to set
	 */
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

}
