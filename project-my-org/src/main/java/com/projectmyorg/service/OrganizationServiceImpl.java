/**
 * 
 */
package com.projectmyorg.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
import com.projectmyorg.exceptions.InternalServerErrorException;
import com.projectmyorg.exceptions.RecordNotFoundException;
import com.projectmyorg.repo.DepartmentRepository;
import com.projectmyorg.repo.EmployeeTypeRepository;
import com.projectmyorg.repo.JobRoleRepository;
import com.projectmyorg.repo.NodeRepository;
import com.projectmyorg.repo.OrgServiceRepository;

/**
 * @author Shankar D
 *
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl.class);

	@Autowired
	private EmployeeTypeRepository empTypeRepo;

	@Autowired
	private DepartmentRepository deptRepo;

	@Autowired
	private JobRoleRepository jobRoleRepo;

	@Autowired
	private OrgServiceRepository orgServiceRepo;
	
	@Autowired
	private NodeRepository nodeRepo;

	@Override
	public ResponseEntity<BaseResponse> saveEmployeeType(EmpTypeDto request) {
		BaseResponse response = new BaseResponse();
		try {
			Optional<EmployeeType> type = empTypeRepo.findByEmpType(request.getEmpType());
			if (type.isPresent()) {
				response.setStatus(false);
				response.setMessage("Employee type with " + request.getEmpType() + " is already exists!");
				LOGGER.warn("Employee type {} is already exists!", request.getEmpType());
			} else {
				EmployeeType emptype = new EmployeeType();
				emptype.setEmpType(request.getEmpType());
				empTypeRepo.save(emptype);
				LOGGER.info("Employee type {} has been created.", request.getEmpType());
			}
		} catch (Exception e) {
			LOGGER.error("An error occured while creating employee type {}", e.getMessage());
			throw new InternalServerErrorException();
		}
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<EmployeeTypesResponse> findAllEmployeeTypes() {
		try {
			LOGGER.info("Fetching all the employee types");
			List<EmployeeType> empTypes = empTypeRepo.findAll();
			return ResponseEntity.ok(new EmployeeTypesResponse(empTypes));
		} catch (Exception e) {
			LOGGER.error("An error occured while fetching employee types {}", e.getMessage());
			throw new InternalServerErrorException();
		}
	}

	@Override
	public EmployeeType findEmployeeType(String empType) {
		return empTypeRepo.findByEmpType(empType)
				.orElseThrow(() -> new RecordNotFoundException("Employee type not found"));
	}

	@Override
	public ResponseEntity<BaseResponse> saveDepartment(DeptDto request) {
		BaseResponse response = new BaseResponse();
		try {
			Optional<Department> deptOpt = deptRepo.findByDept(request.getDept());
			if (deptOpt.isPresent()) {
				response.setStatus(false);
				response.setMessage("Department with " + request.getDept() + " is already exists!");
				LOGGER.warn("Department {} is already exists!", request.getDept());
			} else {
				Department dept = new Department();
				dept.setDept(request.getDept());
				deptRepo.save(dept);
				LOGGER.info("Department {} has been created.", request.getDept());
			}
		} catch (Exception e) {
			LOGGER.error("An error occured while creating dept {}", e.getMessage());
			throw new InternalServerErrorException();
		}
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<DepartmentsResponse> findAllDepartments() {
		try {
			LOGGER.info("Fetching all the departments");
			List<Department> departments = deptRepo.findAll();
			return ResponseEntity.ok(new DepartmentsResponse(departments));
		} catch (Exception e) {
			LOGGER.error("An error occured while fetching departments {}", e.getMessage());
			throw new InternalServerErrorException();
		}
	}

	@Override
	public Department findDepartment(String dept) {
		return deptRepo.findByDept(dept).orElseThrow(() -> new RecordNotFoundException("Department not found"));
	}

	@Override
	public ResponseEntity<BaseResponse> saveJobRole(JobRoleDto request) {
		BaseResponse response = new BaseResponse();
		try {
			Optional<JobRole> roleOpt = jobRoleRepo.findByRoleName(request.getJobRole());
			if (roleOpt.isPresent()) {
				response.setStatus(false);
				response.setMessage("Job role with " + request.getJobRole() + " is already exists!");
				LOGGER.warn("Job role {} is already exists!", request.getJobRole());
			} else {
				JobRole role = new JobRole();
				role.setRoleName(request.getJobRole());
				jobRoleRepo.save(role);
				LOGGER.info("JobRole {} has been created.", request.getJobRole());
			}
		} catch (Exception e) {
			LOGGER.error("An error occured while creating jobrole {}", e.getMessage());
			throw new InternalServerErrorException();
		}
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<JobRolesResponse> findAllJobRoles() {
		try {
			LOGGER.info("Fetching all the job roles");
			List<JobRole> roles = jobRoleRepo.findAll();
			return ResponseEntity.ok(new JobRolesResponse(roles));
		} catch (Exception e) {
			LOGGER.error("An error occured while fetching job roles {}", e.getMessage());
			throw new InternalServerErrorException();
		}
	}

	@Override
	public JobRole findJobRole(String jobRole) {
		return jobRoleRepo.findByRoleName(jobRole).orElseThrow(() -> new RecordNotFoundException("Job role not found"));
	}

	@Override
	public ResponseEntity<BaseResponse> saveOrgService(SingleStrRequestDto request) {
		BaseResponse response = new BaseResponse();
		try {
			Optional<OrgService> serviceOpt = orgServiceRepo.findByName(request.getValue());
			if (serviceOpt.isPresent()) {
				response.setStatus(false);
				response.setMessage("Org service with " + request.getValue() + " is already exists!");
				LOGGER.warn("Org service {} is already exists!", request.getValue());
			} else {
				OrgService service = new OrgService();
				service.setName(request.getValue());
				orgServiceRepo.save(service);
				LOGGER.info("Org service {} has been created.", request.getValue());
			}
		} catch (Exception e) {
			LOGGER.error("An error occured while creating orgservice {}", e.getMessage());
			throw new InternalServerErrorException();
		}
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<CommonResponse> findAllOrgServices() {
		try {
			LOGGER.info("Fetching all the org services");
			List<OrgService> orgServices = orgServiceRepo.findAll();
			return ResponseEntity.ok(new CommonResponse(orgServices));
		} catch (Exception e) {
			LOGGER.error("An error occured while fetching org service {}", e.getMessage());
			throw new InternalServerErrorException();
		}
	}

	@Override
	public OrgService findOrgService(String orgService) {
		return orgServiceRepo.findByName(orgService)
				.orElseThrow(() -> new RecordNotFoundException("Org service not found"));
	}
	
	@Override
	public ResponseEntity<BaseResponse> saveNode(NodeDto request) {
		BaseResponse response = new BaseResponse();
		OrgService service = findOrgService(request.getOrgService());
		try {
			Optional<Node> nodeOtp = nodeRepo.findByNameAndOrgService(request.getName(), service);
			if (nodeOtp.isPresent()) {
				response.setStatus(false);
				response.setMessage("node with " + request.getName() + " is already exists!");
				LOGGER.warn("node {} is already exists!", request.getName());
			} else {
				Node node = new Node();
				node.setName(request.getName());
				node.setOrgService(service);
				nodeRepo.save(node);
				LOGGER.info("Node {} has been created.", request.getName());
			}
		} catch (Exception e) {
			LOGGER.error("An error occured while creating node {}", e.getMessage());
			throw new InternalServerErrorException();
		}
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<CommonResponse> findAllNodes(String serviceName) {
		OrgService service = findOrgService(serviceName);
		try {
			LOGGER.info("Fetching all the nodes");
			List<Node> nodes = nodeRepo.findByOrgService(service);
			return ResponseEntity.ok(new CommonResponse(nodes));
		} catch (Exception e) {
			LOGGER.error("An error occured while fetching nodes {}", e.getMessage());
			throw new InternalServerErrorException();
		}
	}
	
	@Override
	public Node findNodeByNameAndService(String node, String serviceName) {
		OrgService service = findOrgService(serviceName);
		return nodeRepo.findByNameAndOrgService(node, service)
				.orElseThrow(() -> new RecordNotFoundException("Node not found"));
	}
	
}
