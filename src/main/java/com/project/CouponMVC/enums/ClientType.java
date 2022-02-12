package com.project.CouponMVC.enums;

public enum ClientType {

	ADMIN,
	COMPANY,
	CUSTOMER,
	;
	
	/**
	 * 
	 * @param id
	 * @return ClientType where the oridnal number equals to given ID. if no oridnal number equals to the ID, returns null. 
	 */
	public static ClientType getClientTypeById(int id) {
		for (ClientType clientType : values()) {
			if(clientType.ordinal()==id) return clientType;
		}
		return null;
	}
	
	public static ClientType getClientTypeByString(String string) {
		if(string==null)
			return null;
		switch(string.toUpperCase()) {
		case "ADMIN":
			return ADMIN;
		case "COMPANY":
			return COMPANY;
		case "CUSTOMER":
			return CUSTOMER;
		default:
			System.err.println("role fetched ("+string+") does not exist");
			return null;
		}
	}
	
}
