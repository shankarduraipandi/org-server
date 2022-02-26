/**
 * 
 */
package com.projectmyorg.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.projectmyorg.domain.enums.Status;
import com.projectmyorg.domain.masters.Asset;

/**
 * @author Shankar D
 *
 */
@Entity
@Table(name = "asset_requests")
public class AssetRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uuid", unique = true, updatable = false)
	private String uuid;

	@OneToOne(targetEntity = Asset.class)
	@JoinColumn(name = "asset")
	private Asset asset;

	@Column(name = "tenure")
	private Integer tenure;
	
    @Enumerated(EnumType.STRING)
	@Column(length = 25)
	private Status status;
	
	@JoinColumn(name = "requested_by")
	private String requestedBy;
	
	@Column(name = "requested_date")
	private Date requestedDate;
	
	@Column(name = "approved_by")
	private String approvedBy;

	@Column(name = "approved_date")
	private Date approvedDate;
	
	@Column(name = "rejected_by")
	private String rejectedBy;

	@Column(name = "rejected_date")
	private Date rejectedDate;
	
	@Column(name = "is_active")
	private boolean isActive = true;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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

	/**
	 * @return the asset
	 */
	public Asset getAsset() {
		return asset;
	}

	/**
	 * @param asset the asset to set
	 */
	public void setAsset(Asset asset) {
		this.asset = asset;
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
	 * @return the requestedBy
	 */
	public String getRequestedBy() {
		return requestedBy;
	}

	/**
	 * @param requestedBy the requestedBy to set
	 */
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	/**
	 * @return the requestedDate
	 */
	public Date getRequestedDate() {
		return requestedDate;
	}

	/**
	 * @param requestedDate the requestedDate to set
	 */
	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

	/**
	 * @return the approvedBy
	 */
	public String getApprovedBy() {
		return approvedBy;
	}

	/**
	 * @param approvedBy the approvedBy to set
	 */
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	/**
	 * @return the approvedDate
	 */
	public Date getApprovedDate() {
		return approvedDate;
	}

	/**
	 * @param approvedDate the approvedDate to set
	 */
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	/**
	 * @return the rejectedBy
	 */
	public String getRejectedBy() {
		return rejectedBy;
	}

	/**
	 * @param rejectedBy the rejectedBy to set
	 */
	public void setRejectedBy(String rejectedBy) {
		this.rejectedBy = rejectedBy;
	}

	/**
	 * @return the rejectedDate
	 */
	public Date getRejectedDate() {
		return rejectedDate;
	}

	/**
	 * @param rejectedDate the rejectedDate to set
	 */
	public void setRejectedDate(Date rejectedDate) {
		this.rejectedDate = rejectedDate;
	}
	
	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
