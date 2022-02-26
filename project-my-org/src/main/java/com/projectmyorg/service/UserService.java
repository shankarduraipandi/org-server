/**
 * 
 */
package com.projectmyorg.service;

import org.springframework.http.ResponseEntity;

import com.projectmyorg.domain.AppUser;
import com.projectmyorg.domain.enums.ERole;
import com.projectmyorg.domain.masters.Role;
import com.projectmyorg.dto.CommonResponse;
import com.projectmyorg.dto.request.GroupDto;
import com.projectmyorg.dto.request.NodesUserGroupDto;
import com.projectmyorg.dto.request.UserDto;
import com.projectmyorg.dto.response.BaseResponse;
import com.projectmyorg.dto.response.UserGroups;

/**
 * @author Shankar D
 *
 */
public interface UserService {

	Role findRoleByName(ERole role);

	BaseResponse saveUser(UserDto request);

	BaseResponse saveUserGroup(GroupDto request);

	UserGroups findAllUserGroups();

	ResponseEntity<BaseResponse> addNodesToUserGroup(NodesUserGroupDto request);

	AppUser findCurrentUser();

	CommonResponse getUserNodes();

	String findLoggedInUserName();

}
