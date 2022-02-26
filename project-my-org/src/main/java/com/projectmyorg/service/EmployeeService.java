/**
 * 
 */
package com.projectmyorg.service;

import com.projectmyorg.domain.Employee;
import com.projectmyorg.dto.request.EmployeeDto;
import com.projectmyorg.dto.response.BaseResponse;
import com.projectmyorg.dto.response.EmployeeList;

/**
 * @author Shankar D
 *
 */
public interface EmployeeService {

	Employee findEmployeeByUuid(String uuid);

	BaseResponse createEmployee(EmployeeDto request);

	EmployeeList findAllEmployees();

	Employee findCurrentUserEmployee();

}
