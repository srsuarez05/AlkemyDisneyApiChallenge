package com.ssuarez.DisneyApiJava.dto;

import javax.validation.constraints.NotEmpty;

public class LoginDTO {

	@NotEmpty(message="El username o email no puede estar vacio")
	private String usernameOrEmail;
	@NotEmpty(message="El password no puede estar vacio")
	private String password;

	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}