/**
 * 
 */
package com.projectmyorg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmyorg.commons.AppUtils;
import com.projectmyorg.domain.JobLocation;
import com.projectmyorg.domain.masters.City;
import com.projectmyorg.domain.masters.Country;
import com.projectmyorg.domain.masters.State;
import com.projectmyorg.dto.request.JobLocationDto;
import com.projectmyorg.dto.response.BaseResponse;
import com.projectmyorg.dto.response.JobLocationsResponse;
import com.projectmyorg.exceptions.InternalServerErrorException;
import com.projectmyorg.exceptions.RecordNotFoundException;
import com.projectmyorg.repo.JobLocationRepository;

/**
 * @author Shankar D
 *
 */
@Service
public class JobLocationServiceImpl implements JobLocationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobLocationServiceImpl.class);
	
	@Autowired
	private JobLocationRepository jobLocationRepo;
	
	@Autowired
	private CityStateCountryService cityStateCountryService;

	@Override
	public BaseResponse saveJobLocation(JobLocationDto request) {
		Country country = cityStateCountryService.findCountryByName(request.getCountry());
		State state = cityStateCountryService.findStateByNameAndCountry(request.getState(), country);
		City city = cityStateCountryService.findCityByNameAndState(request.getCity(), state);
		try {
			JobLocation jobLocation = new JobLocation();
			jobLocation.setUuid(AppUtils.strUUID());
			jobLocation.setOffice(request.getOffice());
			jobLocation.setAddress(request.getAddress());
			jobLocation.setCity(city);
			jobLocation.setFax(request.getFax());
			jobLocation.setPhone(request.getPhone());
			jobLocation.setEmail(request.getEmail());
			jobLocationRepo.save(jobLocation);
			LOGGER.info("Joblocation created. {}" , jobLocation.getOffice());
			return new BaseResponse();
		} catch (Exception e) {
			LOGGER.error("An error occured while creating Joblocation {}", e.getMessage());
			throw new InternalServerErrorException();
		}
	}
	
	@Override
	public JobLocationsResponse findAllJobLocations() {
		List<JobLocationDto> joblocationDtos = new ArrayList<>();
		try {
			LOGGER.info("Fetching all the Joblocation");
			List<JobLocation> joblocation = jobLocationRepo.findAll();
			if (!joblocation.isEmpty())
				joblocation.stream().forEach(location -> joblocationDtos.add(new JobLocationDto(location)));

		} catch (Exception e) {
			LOGGER.error("An error occured while fetching states {}", e.getMessage());
			throw new InternalServerErrorException();
		}
		LOGGER.info("Returning {} job locations", joblocationDtos.size());
		return new JobLocationsResponse(joblocationDtos);
	}
	
	@Override
	public JobLocation findJobLocationByUUID(String uuid) {
		Optional<JobLocation> jobLocationOpt;
		try {
			LOGGER.info("Finding joblocation by id {}", uuid);
			jobLocationOpt = jobLocationRepo.findByUuid(uuid);
		} catch (Exception e) {
			LOGGER.error("An error occured while getting Joblocation {}. message {}", uuid, e.getMessage());
			throw new InternalServerErrorException();
		}

		if (jobLocationOpt.isEmpty()) {
			LOGGER.warn("Joblocation not found with id {} ", uuid);
			throw new RecordNotFoundException("Joblocation not found with id " + uuid);
		}
		return jobLocationOpt.get();
	}

}
