package com.project.CouponMVC.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.CouponMVC.exceptions.IncorrectCredentials;
import com.project.CouponMVC.repositories.interfaces.CompanyRepositoryInt;
import com.project.CouponMVC.repositories.interfaces.CouponRepositoryInt;
import com.project.CouponMVC.repositories.interfaces.CustomerRepositoryInt;

public abstract class ClientService {

	@Autowired
	protected CompanyRepositoryInt coRep;
	@Autowired
	protected CustomerRepositoryInt cuRep;
	@Autowired
	protected CouponRepositoryInt couRep;
	@Autowired
	protected Conditions conditions;
	
	protected ClientService() {
		super();
	}
	
	/**
	 * 
	 * @param email
	 * @param password
	 * @return this class
	 * @throws IncorrectCredentials
	 * 
	 * returns this class if email and password match (compared from database). else, throws IncorrectCredentials exception.
	 * 
	 */
	protected abstract ClientService login(String email, String password) throws IncorrectCredentials;

	
}
