/**
 * 
 */
package com.projectmyorg.dto.request;

import javax.validation.constraints.NotBlank;

/**
 * @author Shankar D
 *
 */
public class GroupDto {
	
	@NotBlank(message = "GroupName should not be null or empty")
	private String groupName;

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	

}
