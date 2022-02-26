/**
 * 
 */
package com.projectmyorg.dto.request;

import javax.validation.constraints.NotBlank;

import com.projectmyorg.domain.Employee;

/**
 * @author Shankar D
 *
 */
public class EmployeeDto {

	private String uuid;

	@NotBlank(message = "firstName should not be null or empty")
	private String firstName;

	@NotBlank(message = "lastName should not be null or empty")
	private String lastName;

	@NotBlank(message = "email should not be null or empty")
	private String email;

	@NotBlank(message = "address should not be null or empty")
	private String address;

	@NotBlank(message = "city should not be null or empty")
	private String city;

	@NotBlank(message = "state should not be null or empty")
	private String state;

	@NotBlank(message = "country should not be null or empty")
	private String country;

	private String phone;

	@NotBlank(message = "jobRole should not be null or empty")
	private String jobRole;

	@NotBlank(message = "dept should not be null or empty")
	private String dept;

	@NotBlank(message = "employeeType should not be null or empty")
	private String employeeType;

	@NotBlank(message = "jobLocationUuid should not be null or empty")
	private String jobLocationUuid;

	private String bio;

	private String commonManagerId;

	/**
	 * 
	 */
	public EmployeeDto() {
		super();
	}

	/**
	 * 
	 */
	public EmployeeDto(Employee employee) {
		super();
		this.uuid = employee.getUuid();
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.email = employee.getEmail();
		this.address = employee.getAddress();
		this.city = employee.getCity().getCityName();
		this.state = employee.getCity().getState().getStateName();
		this.country = employee.getCity().getState().getCountry().getCountryName();
		this.phone = employee.getPhone();
		this.jobRole = employee.getJobRole().getRoleName();
		this.dept = employee.getDept().getDept();
		this.employeeType = employee.getEmployeeType().getEmpType();
		this.jobLocationUuid = employee.getJobLocation().getUuid();
		this.bio = employee.getBio();
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return the jobRole
	 */
	public String getJobRole() {
		return jobRole;
	}

	/**
	 * @param jobRole the jobRole to set
	 */
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	/**
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}

	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}

	/**
	 * @return the employeeType
	 */
	public String getEmployeeType() {
		return employeeType;
	}

	/**
	 * @param employeeType the employeeType to set
	 */
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	/**
	 * @return the jobLocationUuid
	 */
	public String getJobLocationUuid() {
		return jobLocationUuid;
	}

	/**
	 * @param jobLocationUuid the jobLocationUuid to set
	 */
	public void setJobLocationUuid(String jobLocationUuid) {
		this.jobLocationUuid = jobLocationUuid;
	}

	/**
	 * @return the bio
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * @param bio the bio to set
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * @return the commonManagerId
	 */
	public String getCommonManagerId() {
		return commonManagerId;
	}

	/**
	 * @param commonManagerId the commonManagerId to set
	 */
	public void setCommonManagerId(String commonManagerId) {
		this.commonManagerId = commonManagerId;
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
