/**
 * 
 */
package com.projectmyorg.service;

import org.springframework.http.ResponseEntity;

import com.projectmyorg.domain.masters.Department;
import com.projectmyorg.domain.masters.EmployeeType;
import com.projectmyorg.domain.masters.JobRole;
import com.projectmyorg.domain.masters.Node;
import com.projectmyorg.domain.masters.OrgService;
import com.projectmyorg.dto.CommonResponse;
import com.projectmyorg.dto.SingleStrRequestDto;
import com.projectmyorg.dto.request.DeptDto;
import com.projectmyorg.dto.request.EmpTypeDto;
import com.projectmyorg.dto.request.JobRoleDto;
import com.projectmyorg.dto.request.NodeDto;
import com.projectmyorg.dto.response.BaseResponse;
import com.projectmyorg.dto.response.DepartmentsResponse;
import com.projectmyorg.dto.response.EmployeeTypesResponse;
import com.projectmyorg.dto.response.JobRolesResponse;

/**
 * @author Shankar D
 *
 */
public interface OrganizationService {

	ResponseEntity<BaseResponse> saveEmployeeType(EmpTypeDto request);

	ResponseEntity<EmployeeTypesResponse> findAllEmployeeTypes();

	ResponseEntity<BaseResponse> saveDepartment(DeptDto request);

	ResponseEntity<DepartmentsResponse> findAllDepartments();

	ResponseEntity<BaseResponse> saveJobRole(JobRoleDto request);

	ResponseEntity<JobRolesResponse> findAllJobRoles();

	EmployeeType findEmployeeType(String empType);

	Department findDepartment(String dept);

	JobRole findJobRole(String jobRole);

	ResponseEntity<BaseResponse> saveOrgService(SingleStrRequestDto request);

	ResponseEntity<CommonResponse> findAllOrgServices();

	OrgService findOrgService(String orgService);

	ResponseEntity<BaseResponse> saveNode(NodeDto request);

	ResponseEntity<CommonResponse> findAllNodes(String serviceName);

	Node findNodeByNameAndService(String node, String serviceName);

}
