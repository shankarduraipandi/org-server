/**
 * 
 */
package com.projectmyorg.dto.response;

import java.util.List;

import com.projectmyorg.domain.masters.EmployeeType;

/**
 * @author Shankar D
 *
 */
public class EmployeeTypesResponse extends BaseResponse {
	
    private List<EmployeeType> employeeTypes;
    
	/**
	 * 
	 */
	public EmployeeTypesResponse() {
		super();
	}

	/**
	 * @param data
	 */
	public EmployeeTypesResponse(List<EmployeeType> employeeTypes) {
		super();
		this.employeeTypes = employeeTypes;
	}

	/**
	 * @return the employeeTypes
	 */
	public List<EmployeeType> getEmployeeTypes() {
		return employeeTypes;
	}


	/**
	 * @param employeeTypes the employeeTypes to set
	 */
	public void setEmployeeTypes(List<EmployeeType> employeeTypes) {
		this.employeeTypes = employeeTypes;
	}


}
