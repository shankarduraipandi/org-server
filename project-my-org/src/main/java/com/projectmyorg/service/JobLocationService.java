/**
 * 
 */
package com.projectmyorg.service;

import com.projectmyorg.domain.JobLocation;
import com.projectmyorg.dto.request.JobLocationDto;
import com.projectmyorg.dto.response.BaseResponse;
import com.projectmyorg.dto.response.JobLocationsResponse;

/**
 * @author Shankar D
 *
 */
public interface JobLocationService {

	BaseResponse saveJobLocation(JobLocationDto request);

	JobLocationsResponse findAllJobLocations();

	JobLocation findJobLocationByUUID(String uuid);

}
