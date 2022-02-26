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

import com.projectmyorg.dto.CommonResponse;
import com.projectmyorg.dto.request.GroupDto;
import com.projectmyorg.dto.request.NodesUserGroupDto;
import com.projectmyorg.dto.request.UserDto;
import com.projectmyorg.dto.response.BaseResponse;
import com.projectmyorg.dto.response.UserGroups;
import com.projectmyorg.service.UserService;

/**
 * @author Shankar D
 *
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> saveUser(@Valid @RequestBody UserDto request) {
		return ResponseEntity.ok(userService.saveUser(request));
	}

	@PostMapping("/group")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> saveUserGroup(@Valid @RequestBody GroupDto request) {
		return ResponseEntity.ok(userService.saveUserGroup(request));
	}

	@GetMapping("/group")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<UserGroups> findAllUserGroup() {
		return ResponseEntity.ok(userService.findAllUserGroups());
	}

	@PostMapping("/group/add_nodes")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> addNodesToUserGroups(@Valid @RequestBody NodesUserGroupDto request) {
		return userService.addNodesToUserGroup(request);
	}

	@GetMapping("/node/all")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<CommonResponse> findAllUserNodes() {
		return ResponseEntity.ok(userService.getUserNodes());
	}
	
}