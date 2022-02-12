package com.project.CouponMVC.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.CouponMVC.enums.ClientType;
import com.project.CouponMVC.exceptions.IncorrectCredentials;
import com.project.CouponMVC.services.ClientService;
import com.project.CouponMVC.utils.Credentials;


@RestController
@RequestMapping("/")
public class LoginController extends ClientController{

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody Credentials cred){
		
		String email = cred.getEmail();
		String password = cred.getPassword();
		ClientType clientType = cred.getRole();
		
		try {
			if(clientType==null)
				return new ResponseEntity<String>("Client type is null", HttpStatus.BAD_REQUEST);
			
			ClientService service = lm.login(email, password, clientType);
			String token = tm.getToken(service);
			return new ResponseEntity<String>(token, HttpStatus.OK);
				
		}catch (IncorrectCredentials e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
