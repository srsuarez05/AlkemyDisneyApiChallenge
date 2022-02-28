package com.ssuarez.DisneyApiJava.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RegisterDTO {

	@NotEmpty(message="El campo name no puede estar vacio")
	private String name;
	@NotEmpty(message="El campo username no puede estar vacio")
	private String username;
	@NotEmpty(message="El campo email no puede estar vacio")
	@Email(message="Debe ser un email valido")
	private String email;
	@NotEmpty(message="El campo password no puede estar vacio")
	private String password;
	
	public RegisterDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
