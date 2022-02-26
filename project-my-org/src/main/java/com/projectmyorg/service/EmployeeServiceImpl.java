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
import org.springframework.transaction.annotation.Transactional;

import com.projectmyorg.commons.AppUtils;
import com.projectmyorg.domain.AppUser;
import com.projectmyorg.domain.Employee;
import com.projectmyorg.domain.JobLocation;
import com.projectmyorg.domain.masters.City;
import com.projectmyorg.domain.masters.Country;
import com.projectmyorg.domain.masters.Department;
import com.projectmyorg.domain.masters.EmployeeType;
import com.projectmyorg.domain.masters.JobRole;
import com.projectmyorg.domain.masters.State;
import com.projectmyorg.dto.request.EmployeeDto;
import com.projectmyorg.dto.response.BaseResponse;
import com.projectmyorg.dto.response.EmployeeList;
import com.projectmyorg.exceptions.BadRequestException;
import com.projectmyorg.exceptions.InternalServerErrorException;
import com.projectmyorg.exceptions.RecordNotFoundException;
import com.projectmyorg.repo.EmployeeRepository;

/**
 * @author Shankar D
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CityStateCountryService cityStateCountryService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private JobLocationService jobLocationService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Employee findCurrentUserEmployee() {
		AppUser appUser = userService.findCurrentUser();
		return findEmployeeByUuid(appUser.getUuid());
	}

	@Transactional
	@Override
	public BaseResponse createEmployee(EmployeeDto request) {

		if (employeeExistsByEmail(request.getEmail()))
			throw new BadRequestException("Email already registered!");

		Country country = cityStateCountryService.findCountryByName(request.getCountry());
		State state = cityStateCountryService.findStateByNameAndCountry(request.getState(), country);
		City city = cityStateCountryService.findCityByNameAndState(request.getCity(), state);
		JobRole jobRole = organizationService.findJobRole(request.getJobRole());
		Department dept = organizationService.findDepartment(request.getDept());
		EmployeeType empType = organizationService.findEmployeeType(request.getEmployeeType());
		JobLocation jobLocation = jobLocationService.findJobLocationByUUID(request.getJobLocationUuid());
		try {
			// Employee creation ->
			Employee employee = new Employee();
			employee.setUuid(AppUtils.strUUID());
			employee.setFirstName(request.getFirstName());
			employee.setLastName(request.getLastName());
			employee.setAddress(request.getAddress());
			employee.setCity(city);
			employee.setPhone(request.getPhone());
			employee.setEmail(request.getEmail());
			employee.setJobRole(jobRole);
			employee.setDept(dept);
			employee.setEmployeeType(empType);
			employee.setJobLocation(jobLocation);
			employee.setBio(request.getBio());

			// Finding common manger & mapping with emp
			if (request.getCommonManagerId() != null) {
				Employee commonManager = findEmployeeByUuid(request.getCommonManagerId());
				employee.setCommonManagerId(commonManager.getId());
			}
			
			// Save employee ->
			employeeRepository.save(employee);
			LOGGER.info("Employee save. id {}", employee.getUuid());

			return new BaseResponse();
		} catch (Exception e) {
			LOGGER.error("An error occured while creating country {}", e.getMessage());
			throw new InternalServerErrorException();
		}
	}

	@Override
	public Employee findEmployeeByUuid(String uuid) {
		Optional<Employee> employeeOpt;
		try {
			LOGGER.info("Finding employee by uuid {}", uuid);
			employeeOpt = employeeRepository.findByUuid(uuid);
		} catch (Exception e) {
			LOGGER.error("An error occured while getting employee {}. message {}", uuid, e.getMessage());
			throw new InternalServerErrorException();
		}

		if (employeeOpt.isEmpty()) {
			LOGGER.warn("Employee not found with id {} ", uuid);
			throw new RecordNotFoundException("Employee not found with id " + uuid);
		}
		return employeeOpt.get();
	}

	public boolean employeeExistsByEmail(String email) {
		return employeeRepository.existsByEmail(email);
	}

	@Override
	public EmployeeList findAllEmployees() {
		List<EmployeeDto> dtos = new ArrayList<>();
		try {
			List<Employee> employees = employeeRepository.findAll();
			if (!employees.isEmpty())
				employees.stream().forEach(employee -> dtos.add(new EmployeeDto(employee)));

			LOGGER.info("Returning {} employees", dtos.size());
		} catch (Exception e) {
			LOGGER.error("An error occured while fetching employees. message {}", e.getMessage());
			throw new InternalServerErrorException();
		}
		return new EmployeeList(dtos);
	}
}
