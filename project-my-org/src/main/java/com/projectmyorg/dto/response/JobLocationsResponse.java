/**
 * 
 */
package com.projectmyorg.dto.response;

import java.util.List;

import com.projectmyorg.dto.request.JobLocationDto;

/**
 * @author Shankar D
 *
 */
public class JobLocationsResponse extends BaseResponse {
	
    private List<JobLocationDto> jobLocations;
    
	/**
	 * 
	 */
	public JobLocationsResponse() {
		super();
	}

	/**
	 * @param data
	 */
	public JobLocationsResponse(List<JobLocationDto> jobLocations) {
		super();
		this.jobLocations = jobLocations;
	}

	/**
	 * @return the jobLocations
	 */
	public List<JobLocationDto> getJobLocations() {
		return jobLocations;
	}

	/**
	 * @param jobLocations the jobLocations to set
	 */
	public void setJobLocations(List<JobLocationDto> jobLocations) {
		this.jobLocations = jobLocations;
	}

}
