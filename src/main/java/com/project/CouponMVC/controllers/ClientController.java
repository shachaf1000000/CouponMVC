package com.project.CouponMVC.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.CouponMVC.services.LoginManager;
import com.project.CouponMVC.tokens.TokenManager;

public abstract class ClientController {

	protected static final String NOT_HANDLED = "Exception not handled";
	
	@Autowired
	protected TokenManager tm;
	@Autowired
	protected LoginManager lm;
}
