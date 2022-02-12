package com.project.CouponMVC.jsons;

import com.project.CouponMVC.entities.Company;
import com.project.CouponMVC.entities.Coupon;
import com.project.CouponMVC.entities.Customer;

public class JsonFactory {

	public static Company createCompany(JsonCompany json) {
		Company company = new Company(json.getId() ,json.getName(), json.getEmail(), json.getPassword());
		return company;
	}
	
	public static Coupon createCoupon(JsonCoupon json) {
		Coupon coupon = new Coupon(json.getId(), json.getCategory(), json.getTitle(), json.getDescription(), json.getStartDate(), json.getEndDate(), json.getAmount(), json.getPrice(), json.getImage());
		return coupon;
	}
	
	public static Customer createCustomer(JsonCustomer json) {
		Customer customer = new Customer(json.getId(), json.getFirstname(), json.getLastname(), json.getEmail(), json.getPassword());
		return customer;
	}
	
}
