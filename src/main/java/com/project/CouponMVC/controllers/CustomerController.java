package com.project.CouponMVC.controllers;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.CouponMVC.entities.Coupon;
import com.project.CouponMVC.entities.Customer;
import com.project.CouponMVC.enums.Category;
import com.project.CouponMVC.exceptions.ConditionsNotMet;
import com.project.CouponMVC.exceptions.TokenDoesNotExist;
import com.project.CouponMVC.services.CustomerService;


@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController extends ClientController {
	
	@PostMapping("/purchaseCoupon")
	public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
		try {
			CustomerService service = (CustomerService)tm.getService(token);
			service.purchaseCoupon(coupon);
			return new ResponseEntity<String>("Coupon bought successfully", HttpStatus.OK);
		} catch (ConditionsNotMet | TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
			
	@GetMapping("/getCustomerCoupons")
	public ResponseEntity<?> getCustomerCoupons(@RequestHeader("token") String token) {
		try {
			CustomerService service = (CustomerService)tm.getService(token);
			List<Coupon> coupons = service.getAllPurchasedCoupons();
			return new ResponseEntity<List<Coupon>>(coupons, HttpStatus.OK);
		} catch (TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getCustomerCouponsByCategory/{category}")
	public ResponseEntity<?> getCustomerCouponsByCategory(@PathVariable Category category,@RequestHeader("token") String token) {
		try {
			CustomerService service = (CustomerService)tm.getService(token);
			List<Coupon> coupons = service.getAllPurchasedCoupons(category);
			return new ResponseEntity<List<Coupon>>(coupons, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getCustomerCouponsByMaxPrice/{maxPrice}")
	public ResponseEntity<?> getCustomerCouponsByMaxPrice(@PathVariable int maxPrice,@RequestHeader("token") String token) {
		try {
			CustomerService service = (CustomerService)tm.getService(token);
			List<Coupon> coupons = service.getAllPurchasedCoupons(maxPrice);
			return new ResponseEntity<List<Coupon>>(coupons, HttpStatus.OK);
		} catch (TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getAllCoupons")
	public ResponseEntity<?> getAllCoupons(@RequestHeader("token") String token) {
		try {
			CustomerService service = (CustomerService)tm.getService(token);
			List<Coupon> coupons = service.getAllCoupons();
			return new ResponseEntity<List<Coupon>>(coupons, HttpStatus.OK);
		} catch (TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getDetails")
	public ResponseEntity<?> getCustomerDetails(@RequestHeader("token") String token){
		try {
			CustomerService service = (CustomerService)tm.getService(token);
			Customer customer = service.getCustomer();
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} 
	}
}
