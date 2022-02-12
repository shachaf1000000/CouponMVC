package com.project.CouponMVC.jsons;

public class JsonCustomer {

	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	
	
	public JsonCustomer(int id, String firstname, String lastname, String email, String password) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	
	
}
