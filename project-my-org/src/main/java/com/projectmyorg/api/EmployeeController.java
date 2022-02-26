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
import org.springframework.web.bind.annotation.RestController;

import com.projectmyorg.dto.request.EmployeeDto;
import com.projectmyorg.dto.response.BaseResponse;
import com.projectmyorg.dto.response.EmployeeList;
import com.projectmyorg.service.EmployeeService;

/**
 * @author Shankar D
 *
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> saveEmployee(@Valid @RequestBody EmployeeDto request) {
		return ResponseEntity.ok(employeeService.createEmployee(request));
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<EmployeeList> findAllEmployees() {
		return ResponseEntity.ok(employeeService.findAllEmployees());
	}

}