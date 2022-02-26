/**
 * 
 */
package com.projectmyorg.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.projectmyorg.domain.enums.ERole;

/**
 * @author Shankar D
 *
 */
public class UserDto {
	
	@NotBlank(message = "username should not be null or empty")
	private String username;
	
	@NotBlank(message = "password should not be null or empty")
	private String password;
	
	@NotNull(message = "roleType should not be null or empty")
	private ERole roleType;
	
	@NotBlank(message = "empUuid should not be null or empty")
	private String empUuid;
	
	private String userGroup;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the roleType
	 */
	public ERole getRoleType() {
		return roleType;
	}

	/**
	 * @param roleType the roleType to set
	 */
	public void setRoleType(ERole roleType) {
		this.roleType = roleType;
	}

	/**
	 * @return the empUuid
	 */
	public String getEmpUuid() {
		return empUuid;
	}

	/**
	 * @param empUuid the empUuid to set
	 */
	public void setEmpUuid(String empUuid) {
		this.empUuid = empUuid;
	}

	/**
	 * @return the userGroup
	 */
	public String getUserGroup() {
		return userGroup;
	}

	/**
	 * @param userGroup the userGroup to set
	 */
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

}
