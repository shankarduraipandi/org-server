/**
 * 
 */
package com.projectmyorg.dto.response;

import java.util.List;

import com.projectmyorg.domain.masters.Group;

/**
 * @author Shankar D
 *
 */
public class UserGroups extends BaseResponse {

	private List<Group> groups;

	/**
	 * 
	 */
	public UserGroups() {
		super();
	}

	/**
	 * @param data
	 */
	public UserGroups(List<Group> groups) {
		super();
		this.groups = groups;
	}

	/**
	 * @return the groups
	 */
	public List<Group> getGroups() {
		return groups;
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
}
