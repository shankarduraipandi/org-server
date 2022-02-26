/**
 * 
 */
package com.projectmyorg.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.projectmyorg.domain.masters.Asset;

/**
 * @author Shankar D
 *
 */
@Entity
@Table(name = "asset_history")
public class AssetHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(targetEntity = Asset.class)
	@JoinColumn(name = "asset")
	private Asset asset;

	@Basic
	@Temporal(TemporalType.DATE)
	@Column(name = "enroll_date")
	private Date enrollDate;

	@Basic
	@Temporal(TemporalType.DATE)
	@Column(name = "enroll_expiry_date")
	private Date enrollExpiryDate;

	@Column(name = "currently_owned_by")
	private String currentlyOwnedBy;

	@Column(name = "asset_request_id")
	private Long assetRequestId;

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
	 * @return the enrollDate
	 */
	public Date getEnrollDate() {
		return enrollDate;
	}

	/**
	 * @param enrollDate the enrollDate to set
	 */
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	/**
	 * @return the enrollExpiryDate
	 */
	public Date getEnrollExpiryDate() {
		return enrollExpiryDate;
	}

	/**
	 * @param enrollExpiryDate the enrollExpiryDate to set
	 */
	public void setEnrollExpiryDate(Date enrollExpiryDate) {
		this.enrollExpiryDate = enrollExpiryDate;
	}

	/**
	 * @return the currentlyOwnedBy
	 */
	public String getCurrentlyOwnedBy() {
		return currentlyOwnedBy;
	}

	/**
	 * @param currentlyOwnedBy the currentlyOwnedBy to set
	 */
	public void setCurrentlyOwnedBy(String currentlyOwnedBy) {
		this.currentlyOwnedBy = currentlyOwnedBy;
	}

	/**
	 * @return the assetRequestId
	 */
	public Long getAssetRequestId() {
		return assetRequestId;
	}

	/**
	 * @param assetRequestId the assetRequestId to set
	 */
	public void setAssetRequestId(Long assetRequestId) {
		this.assetRequestId = assetRequestId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
