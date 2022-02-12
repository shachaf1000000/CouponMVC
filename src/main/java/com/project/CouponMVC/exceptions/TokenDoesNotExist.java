package com.project.CouponMVC.exceptions;

public class TokenDoesNotExist extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3971412828338700388L;
	
	private static final String ERROR_MESSAGE = "Token expired or does not exist";
	
	public TokenDoesNotExist() {
		super(ERROR_MESSAGE);
	}
}
