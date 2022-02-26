/**
 * 
 */
package com.projectmyorg.dto.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Shankar D
 *
 */
public class AssetDto {
	
	@NotBlank(message = "Assest No should not be null or empty!")
	private String assetNo;

	@NotBlank(message = "Assest name should not be null or empty!")
	private String name;

	@NotBlank(message = "Assest brand should not be null or empty!")
	private String brand;

	@NotBlank(message = "Assest configuration should not be null or empty!")
	private String config;

	@NotBlank(message = "Assest category should not be null or empty!")
	private String category;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date enrollmentDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date lastServiceDate;

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
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
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
	
}
