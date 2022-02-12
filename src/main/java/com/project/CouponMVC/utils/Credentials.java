package com.project.CouponMVC.utils;

import com.project.CouponMVC.enums.ClientType;

public class Credentials {

	private String email;
	private String password;
	private String role;
	
	public Credentials(String email, String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public ClientType getRole() {
		return ClientType.getClientTypeByString(role);
	}

	@Override
	public String toString() {
		return "Credentials [email=" + email + ", password=" + password + ", role=" + role + "]";
	}



	
	
}
