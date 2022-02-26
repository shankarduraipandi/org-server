/**
 * 
 */
package com.projectmyorg.dto.response;

import java.util.List;

import com.projectmyorg.dto.request.EmployeeDto;

/**
 * @author Shankar D
 *
 */
public class EmployeeList extends BaseResponse {

	private List<EmployeeDto> employees;

	/**
	 * 
	 */
	public EmployeeList() {
		super();
	}

	/**
	 * @param data
	 */
	public EmployeeList(List<EmployeeDto> employees) {
		super();
		this.employees = employees;
	}

	/**
	 * @return the employees
	 */
	public List<EmployeeDto> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}
	

}
