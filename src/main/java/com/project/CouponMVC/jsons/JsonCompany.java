package com.project.CouponMVC.jsons;


public class JsonCompany {

	private int id;
	private String name;
	private String email;
	private String password;
	
	
	public JsonCompany(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}

	
	
}
