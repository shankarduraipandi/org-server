/**
 * 
 */
package com.projectmyorg.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * @author Shankar D
 *
 */
public class NodesUserGroupDto {
	
	@NotNull(message = "GroupId should not be empty or null!")
	private Long groupId;
	
	private List<Long> nodeIds;
	
	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the nodeIds
	 */
	public List<Long> getNodeIds() {
		return nodeIds;
	}

	/**
	 * @param nodeIds the nodeIds to set
	 */
	public void setNodeIds(List<Long> nodeIds) {
		this.nodeIds = nodeIds;
	}
	
}
