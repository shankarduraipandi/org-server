/**
 * 
 */
package com.projectmyorg.dto.response;

import java.util.List;

import com.projectmyorg.domain.masters.JobRole;

/**
 * @author Shankar D
 *
 */
public class JobRolesResponse extends BaseResponse {

	private List<JobRole> jobRoles;

	/**
	 * 
	 */
	public JobRolesResponse() {
		super();
	}

	/**
	 * @param data
	 */
	public JobRolesResponse(List<JobRole> jobRoles) {
		super();
		this.jobRoles = jobRoles;
	}

	/**
	 * @return the jobRoles
	 */
	public List<JobRole> getJobRoles() {
		return jobRoles;
	}

	/**
	 * @param jobRoles the jobRoles to set
	 */
	public void setJobRoles(List<JobRole> jobRoles) {
		this.jobRoles = jobRoles;
	}

}
