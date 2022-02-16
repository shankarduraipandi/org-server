/**
 * 
 */
package com.projectmyorg.dto.request;

import javax.validation.constraints.NotBlank;

/**
 * @author Shankar D
 *
 */
public class LoginRequest {

	@NotBlank(message = "Username should not be null or empty!")
	private String usernameOrEmail;

	@NotBlank(message = "Password should not be null or empty!")
	private String password;

	/**
	 * @return the usernameOrEmail
	 */
	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	/**
	 * @param usernameOrEmail the usernameOrEmail to set
	 */
	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	

}
