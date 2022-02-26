package com.projectmyorg.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectmyorg.domain.AppUser;
import com.projectmyorg.domain.AuthUserDetails;
import com.projectmyorg.domain.Employee;
import com.projectmyorg.domain.enums.ERole;
import com.projectmyorg.domain.masters.Group;
import com.projectmyorg.domain.masters.Node;
import com.projectmyorg.domain.masters.Role;
import com.projectmyorg.dto.CommonResponse;
import com.projectmyorg.dto.request.GroupDto;
import com.projectmyorg.dto.request.NodesUserGroupDto;
import com.projectmyorg.dto.request.UserDto;
import com.projectmyorg.dto.response.BaseResponse;
import com.projectmyorg.dto.response.UserGroups;
import com.projectmyorg.exceptions.BadRequestException;
import com.projectmyorg.exceptions.InternalServerErrorException;
import com.projectmyorg.exceptions.RecordNotFoundException;
import com.projectmyorg.repo.GroupRepository;
import com.projectmyorg.repo.NodeRepository;
import com.projectmyorg.repo.RoleRepository;
import com.projectmyorg.repo.UserRepository;

/**
 * @author Shankar D
 *
 */
@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private NodeRepository nodeRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		AppUser user = userRepository.findByEmailOrUsername(usernameOrEmail, usernameOrEmail).orElseThrow(
				() -> new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));
		return new AuthUserDetails(user);
	}
	
	@Override
	public AppUser findCurrentUser() {
		AuthUserDetails userDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		LOGGER.info("Getting current user {}", userDetails.getUsername());
		return findUserById(userDetails.getId());
	}
	
	@Override
	public String findLoggedInUserName() {
		AuthUserDetails userDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		LOGGER.info("Getting logged In usernane {}", userDetails.getUsername());
		return userDetails.getUsername();
	}
	
	
	public AppUser findUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User not foud"));
	}

	@Override
	public Role findRoleByName(ERole role) {
		return roleRepository.findByName(role).orElseThrow(() -> new RecordNotFoundException("Role not foud"));
	}
	
	public Group findGroupByName(String name) {
		return groupRepository.findByName(name).orElseThrow(() -> new RecordNotFoundException("Group not foud"));
	}

	@Override
	@Transactional
	public BaseResponse saveUser(UserDto request) {

		if (userExistsByUsername(request.getUsername()))
			throw new BadRequestException("username already taken!");
		
		if (userExistsByUuid(request.getEmpUuid()))
			throw new BadRequestException("user already exists against this employee!");

		AppUser user = new AppUser();
		Role role = findRoleByName(request.getRoleType());
		Employee employee = employeeService.findEmployeeByUuid(request.getEmpUuid());
		
		if (request.getUserGroup() != null) {
			Group group = findGroupByName(request.getUserGroup());
			user.setGroup(group);
		}
		try {
			user.setUuid(employee.getUuid());
			user.setUsername(request.getUsername());
			user.setEmail(employee.getEmail());
			user.setPassword(passwordEncoder.encode(request.getPassword()));
			user.setRoles(Arrays.asList(role));
			userRepository.save(user);

			LOGGER.info("User created with username {}", user.getUsername());
			return new BaseResponse();
		} catch (Exception e) {
			LOGGER.error("An error occured while creating user {}", e.getMessage());
			throw new InternalServerErrorException();
		}
	}

	@Override
	public BaseResponse saveUserGroup(GroupDto request) {
		if (groupExistsByName(request.getGroupName()))
			throw new BadRequestException("Group already exists!");

		try {
			Group group = new Group();
			group.setName(request.getGroupName());
			groupRepository.save(group);

			LOGGER.info("Group created with name {}", group.getName());
			return new BaseResponse();
		} catch (Exception e) {
			LOGGER.error("An error occured while creating group {}", e.getMessage());
			throw new InternalServerErrorException();
		}
	}
	
	@Override
	public UserGroups findAllUserGroups() {
		try {
			List<Group> groups = groupRepository.findAll();

			LOGGER.info("Returning {} employees", groups.size());
			return new UserGroups(groups);
		} catch (Exception e) {
			LOGGER.error("An error occured while fetching employees. message {}", e.getMessage());
			throw new InternalServerErrorException();
		}
	}
	
	public boolean userExistsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	
	public boolean userExistsByUuid(String uuid) {
		return userRepository.existsByUuid(uuid);
	}
	
	public boolean groupExistsByName(String name) {
		return groupRepository.existsByName(name);
	}
	
	@Override
	public ResponseEntity<BaseResponse> addNodesToUserGroup(NodesUserGroupDto request) {

		Group group = groupRepository.findById(request.getGroupId())
				.orElseThrow(() -> new RecordNotFoundException("Group not foud"));

		if (request.getNodeIds() == null || request.getNodeIds().isEmpty())
			throw new BadRequestException("Node ids should not be empty");

		try {
			List<Node> nodes = nodeRepo.findAllById(request.getNodeIds());
			if (!nodes.isEmpty()) {
				Set<Node> nodeSets = group.getNodes();
				nodeSets.addAll(nodes);
				group.setNodes(nodeSets);
				groupRepository.save(group);

				LOGGER.info("Nodes added into group successfully");
			}
			return ResponseEntity.ok(new BaseResponse());
		} catch (Exception e) {
			LOGGER.error("An error occured while adding node into user groups {}", e.getMessage());
			throw new InternalServerErrorException();
		}
	}
	
	@Override
	public CommonResponse getUserNodes() {
		AppUser user = findCurrentUser();
		Set<Node> nodes = (user.getGroup() != null) ? user.getGroup().getNodes() : user.getNodes();
		if (nodes == null || nodes.isEmpty())
			return new CommonResponse(Collections.emptyMap());

		try {
			Map<String, List<Node>> nodesPerService = nodes.stream()
					.collect(Collectors.groupingBy(this::getOrgServiceName));
			LOGGER.info("Returning node grouping by services");
			return new CommonResponse(nodesPerService);
		} catch (Exception e) {
			LOGGER.error("An error occured while fetching user nodes {}", e.getMessage());
			throw new InternalServerErrorException();
		}
	}

	private String getOrgServiceName(Node node) {
		return node.getOrgService().getName();
	}

}
