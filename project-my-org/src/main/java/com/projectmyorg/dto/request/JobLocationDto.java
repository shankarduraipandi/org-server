/**
 * 
 */
package com.projectmyorg.dto.request;

import javax.validation.constraints.NotBlank;

import com.projectmyorg.domain.JobLocation;

/**
 * @author Shankar D
 *
 */
public class JobLocationDto {
	
	private String uuid;
	
	@NotBlank(message = "office should not be null or empty")
	private String office;
	
	@NotBlank(message = "address should not be null or empty")
	private String address;
	
	@NotBlank(message = "city should not be null or empty")
	private String city;
	
	@NotBlank(message = "state should not be null or empty")
	private String state;
	
	@NotBlank(message = "country should not be null or empty")
	private String country;
	
	private String fax;
	
	private String phone;
	
	@NotBlank(message = "email should not be null or empty")
	private String email;
	
	
	/**
	 * 
	 */
	public JobLocationDto() {
		super();
	}

	/**
	 * @param location
	 */
	public JobLocationDto(JobLocation location) {
		super();
		this.uuid = location.getUuid();
		this.office = location.getOffice();
		this.address = location.getAddress();
		this.city = location.getCity().getCityName();
		this.state = location.getCity().getState().getStateName();
		this.country = location.getCity().getState().getCountry().getCountryName();
		this.fax = location.getFax();
		this.phone = location.getPhone();
		this.email = location.getEmail();
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
	 * @return the office
	 */
	public String getOffice() {
		return office;
	}

	/**
	 * @param office the office to set
	 */
	public void setOffice(String office) {
		this.office = office;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
