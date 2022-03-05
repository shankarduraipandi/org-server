/**
 * 
 */
package com.projectmyorg.domain.masters;

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
import javax.validation.constraints.NotBlank;

/**
 * @author Shankar D
 *
 */
@Entity
@Table(name = "assets")
public class Asset implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name = "asset_no", columnDefinition = "varchar(65)")
	private String assetNo;
	
	@NotBlank
	@Column(name = "name", columnDefinition = "varchar(65)")
	private String name;
	
	@NotBlank
	@Column(name = "brand", columnDefinition = "varchar(90)")
	private String brand;
	
	@NotBlank
	@Column(name = "config", columnDefinition = "varchar(255)")
	private String config;
	
	@OneToOne(targetEntity = AssetCategory.class)
	@JoinColumn(name = "asset_category")
	private AssetCategory assetCategory;
	
	@Basic
	@Temporal(TemporalType.DATE)
	@Column(name = "enrollment_date")
	private Date enrollmentDate;
	
	@Basic
	@Temporal(TemporalType.DATE)
	@Column(name = "last_service_date")
	private Date lastServiceDate;
	
	@Basic
	@Temporal(TemporalType.DATE)
	@Column(name = "last_user_enroll_expiry")
	private Date lastUserEnrollExpiry;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the config
	 */
	public String getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(String config) {
		this.config = config;
	}
	
	/**
	 * @return the assetCategory
	 */
	public AssetCategory getAssetCategory() {
		return assetCategory;
	}

	/**
	 * @param assetCategory the assetCategory to set
	 */
	public void setAssetCategory(AssetCategory assetCategory) {
		this.assetCategory = assetCategory;
	}

	/**
	 * @return the enrollmentDate
	 */
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}

	/**
	 * @param enrollmentDate the enrollmentDate to set
	 */
	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	/**
	 * @return the lastServiceDate
	 */
	public Date getLastServiceDate() {
		return lastServiceDate;
	}

	/**
	 * @param lastServiceDate the lastServiceDate to set
	 */
	public void setLastServiceDate(Date lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}
	
	/**
	 * @return the lastUserEnrollExpiry
	 */
	public Date getLastUserEnrollExpiry() {
		return lastUserEnrollExpiry;
	}

	/**
	 * @param lastUserEnrollExpiry the lastUserEnrollExpiry to set
	 */
	public void setLastUserEnrollExpiry(Date lastUserEnrollExpiry) {
		this.lastUserEnrollExpiry = lastUserEnrollExpiry;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
