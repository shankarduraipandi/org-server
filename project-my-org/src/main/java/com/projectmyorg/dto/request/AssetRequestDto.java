/**
 * 
 */
package com.projectmyorg.dto.request;

import com.projectmyorg.domain.enums.Status;

/**
 * @author Shankar D
 *
 */
public class AssetRequestDto {

	private String assetNo;
	private Integer tenure;
	
	private String uuid;
	private Status status;

	/**
	 * @return the assetNo
	 */
	public String getAssetNo() {
		return assetNo;
	}

	/**
	 * @param assetNo the assetNo to set
	 */
	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	/**
	 * @return the tenure
	 */
	public Integer getTenure() {
		return tenure;
	}

	/**
	 * @param tenure the tenure to set
	 */
	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
	
}
