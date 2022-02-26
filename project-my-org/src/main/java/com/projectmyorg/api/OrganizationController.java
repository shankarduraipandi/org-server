/**
 * 
 */
package com.projectmyorg.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectmyorg.dto.CommonResponse;
import com.projectmyorg.dto.SingleStrRequestDto;
import com.projectmyorg.dto.request.CityStateCountryDto;
import com.projectmyorg.dto.request.DeptDto;
import com.projectmyorg.dto.request.EmpTypeDto;
import com.projectmyorg.dto.request.JobLocationDto;
import com.projectmyorg.dto.request.JobRoleDto;
import com.projectmyorg.dto.request.NodeDto;
import com.projectmyorg.dto.response.BaseResponse;
import com.projectmyorg.dto.response.CityStateCountryResponse;
import com.projectmyorg.dto.response.DepartmentsResponse;
import com.projectmyorg.dto.response.EmployeeTypesResponse;
import com.projectmyorg.dto.response.JobLocationsResponse;
import com.projectmyorg.dto.response.JobRolesResponse;
import com.projectmyorg.service.CityStateCountryService;
import com.projectmyorg.service.JobLocationService;
import com.projectmyorg.service.OrganizationService;

/**
 * @author Shankar D
 *
 */
@RestController
@RequestMapping("/api/v1/org/")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private CityStateCountryService cityStateCountryService;

	@Autowired
	private JobLocationService jobLocationService;

	@PostMapping("/emp_type")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> saveEmployeeType(@Valid @RequestBody EmpTypeDto request) {
		return organizationService.saveEmployeeType(request);
	}

	@GetMapping("/emp_type")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<EmployeeTypesResponse> findAllEmployeeTypes() {
		return organizationService.findAllEmployeeTypes();
	}

	@PostMapping("/dept")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> saveDepartment(@Valid @RequestBody DeptDto request) {
		return organizationService.saveDepartment(request);
	}

	@GetMapping("/dept")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<DepartmentsResponse> findAllDepartments() {
		return organizationService.findAllDepartments();
	}

	@PostMapping("/job_role")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> saveJobRole(@Valid @RequestBody JobRoleDto request) {
		return organizationService.saveJobRole(request);
	}

	@GetMapping("/job_role")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<JobRolesResponse> findAllJobRoles() {
		return organizationService.findAllJobRoles();
	}

	@PostMapping("/city")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> saveCity(@Valid @RequestBody CityStateCountryDto request) {
		return ResponseEntity.ok(cityStateCountryService.saveCity(request));
	}

	@GetMapping("/city")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<CityStateCountryResponse> findAllCities(@RequestParam(name = "country") String countryName,
			@RequestParam(name = "state") String stateName) {
		return ResponseEntity.ok(cityStateCountryService.findAllCitiesByStateAndCountry(stateName, countryName));
	}

	@PostMapping("/state")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> saveState(@Valid @RequestBody CityStateCountryDto request) {
		return ResponseEntity.ok(cityStateCountryService.saveState(request));
	}

	@GetMapping("/state")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<CityStateCountryResponse> findAllStates(@RequestParam(name = "country") String countryName) {
		return ResponseEntity.ok(cityStateCountryService.findAllStatesByCountry(countryName));
	}

	@PostMapping("/country")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> saveCountry(@Valid @RequestBody CityStateCountryDto request) {
		return ResponseEntity.ok(cityStateCountryService.saveCountry(request));
	}

	@GetMapping("/country")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<CityStateCountryResponse> findAllCountries() {
		return ResponseEntity.ok(cityStateCountryService.findAllCountries());
	}

	@PostMapping("/job_location")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> saveJobLocation(@Valid @RequestBody JobLocationDto request) {
		return ResponseEntity.ok(jobLocationService.saveJobLocation(request));
	}

	@GetMapping("/job_location")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<JobLocationsResponse> findAllJobLocations() {
		return ResponseEntity.ok(jobLocationService.findAllJobLocations());
	}
	
	@PostMapping("/service")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> saveOrgService(@Valid @RequestBody SingleStrRequestDto request) {
		return organizationService.saveOrgService(request);
	}
	
	@GetMapping("/service/all")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<CommonResponse> findAllServices() {
		return organizationService.findAllOrgServices();
	}
	
	@PostMapping("/node")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> saveNodes(@Valid @RequestBody NodeDto request) {
		return organizationService.saveNode(request);
	}

	@GetMapping("/node/all")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<CommonResponse> findAllNodes(@RequestParam("service") String serviceName) {
		return organizationService.findAllNodes(serviceName);
	}

}
