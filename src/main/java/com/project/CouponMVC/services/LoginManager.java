package com.project.CouponMVC.services;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.project.CouponMVC.CouponSpringApplication;
import com.project.CouponMVC.enums.ClientType;
import com.project.CouponMVC.exceptions.IncorrectCredentials;

@Service
public class LoginManager {

	public ClientService login(String email, String password, ClientType clientType) throws IncorrectCredentials {
		
		ConfigurableApplicationContext ctx = CouponSpringApplication.getCtx();
		
		switch(clientType) {
		case ADMIN: 
			AdminService adminS = ctx.getBean(AdminService.class);
			return adminS.login(email, password);
		case COMPANY:
			CompanyService companyS = ctx.getBean(CompanyService.class);
			return companyS.login(email, password);
		case CUSTOMER:
			CustomerService customerS = ctx.getBean(CustomerService.class);
			return customerS.login(email, password);
		default:
			return null;
		}
	}
	
}
