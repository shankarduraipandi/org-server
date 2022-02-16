/**
 * 
 */
package com.projectmyorg.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.projectmyorg.domain.masters.City;
import com.projectmyorg.domain.masters.Department;
import com.projectmyorg.domain.masters.EmployeeType;
import com.projectmyorg.domain.masters.JobRole;

/**
 * @author Shankar D
 *
 */
@Entity
@Table(name = "employees")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "uuid", unique = true, updatable = false)
	private String uuid;

	@Size(max = 35)
	private String firstName;

	@Size(max = 35)
	private String lastName;

	@Column(name = "address", columnDefinition = "varchar(255)")
	private String address;

	@OneToOne(targetEntity = City.class)
	@JoinColumn(name = "city")
	private City city;

	@Column(name = "phone", columnDefinition = "varchar(85)")
	private String phone;

	@Column(name = "email", columnDefinition = "varchar(85)")
	private String email;

	@OneToOne(targetEntity = JobRole.class)
	@JoinColumn(name = "job_role")
	private JobRole jobRole;

	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept")
	private Department dept;

	@OneToOne(targetEntity = EmployeeType.class)
	@JoinColumn(name = "employee_type")
	private EmployeeType employeeType;

	@OneToOne(targetEntity = JobLocation.class)
	@JoinColumn(name = "job_location")
	private JobLocation jobLocation;

	@Column(name = "bio", columnDefinition = "text")
	private String bio;
	
	@Column(name = "common_manager_id")
	private Long commonManagerId;

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
	public City getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
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

	/**
	 * @return the jobRole
	 */
	public JobRole getJobRole() {
		return jobRole;
	}

	/**
	 * @param jobRole the jobRole to set
	 */
	public void setJobRole(JobRole jobRole) {
		this.jobRole = jobRole;
	}

	/**
	 * @return the dept
	 */
	public Department getDept() {
		return dept;
	}

	/**
	 * @param dept the dept to set
	 */
	public void setDept(Department dept) {
		this.dept = dept;
	}

	/**
	 * @return the employeeType
	 */
	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	/**
	 * @param employeeType the employeeType to set
	 */
	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	/**
	 * @return the jobLocation
	 */
	public JobLocation getJobLocation() {
		return jobLocation;
	}

	/**
	 * @param jobLocation the jobLocation to set
	 */
	public void setJobLocation(JobLocation jobLocation) {
		this.jobLocation = jobLocation;
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
	public Long getCommonManagerId() {
		return commonManagerId;
	}

	/**
	 * @param commonManagerId the commonManagerId to set
	 */
	public void setCommonManagerId(Long commonManagerId) {
		this.commonManagerId = commonManagerId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
