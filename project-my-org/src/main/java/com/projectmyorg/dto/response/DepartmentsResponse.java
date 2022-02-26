/**
 * 
 */
package com.projectmyorg.dto.response;

import java.util.List;

import com.projectmyorg.domain.masters.Department;

/**
 * @author Shankar D
 *
 */
public class DepartmentsResponse extends BaseResponse {

	private List<Department> depts;

	/**
	 * 
	 */
	public DepartmentsResponse() {
		super();
	}

	/**
	 * @param data
	 */
	public DepartmentsResponse(List<Department> depts) {
		super();
		this.depts = depts;
	}

	/**
	 * @return the depts
	 */
	public List<Department> getDepts() {
		return depts;
	}

	/**
	 * @param depts the depts to set
	 */
	public void setDepts(List<Department> depts) {
		this.depts = depts;
	}
	

}
