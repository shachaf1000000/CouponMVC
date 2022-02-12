package com.project.CouponMVC.controllers;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.CouponMVC.entities.Company;
import com.project.CouponMVC.entities.Coupon;
import com.project.CouponMVC.enums.Category;
import com.project.CouponMVC.exceptions.ConditionsNotMet;
import com.project.CouponMVC.exceptions.TokenDoesNotExist;
import com.project.CouponMVC.services.CompanyService;


@RestController
@RequestMapping("company")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompanyController extends ClientController {

	@PostMapping("addCoupon")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token)  {
		try {
			System.out.println("entered with coupon: " + coupon);
			CompanyService service = (CompanyService)tm.getService(token);
			service.addCoupon(coupon);
			return new ResponseEntity<String>("Coupon added successfully", HttpStatus.OK);
		} catch (ConditionsNotMet | TokenDoesNotExist e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("updateCoupon")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
		try {
			CompanyService service = (CompanyService)tm.getService(token);
			service.updateCoupon(coupon);
			new ResponseEntity<String>("Coupon updated successfully", HttpStatus.OK);
		} catch (ConditionsNotMet | TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(NOT_HANDLED, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("deleteCoupon/{companyId}")
	public ResponseEntity<?> deleteCoupon(@PathVariable int couponId, @RequestHeader("token") String token) {
		try {
			CompanyService service = (CompanyService)tm.getService(token);
			service.deleteCoupon(couponId);
			new ResponseEntity<String>("Coupon deleted successfully", HttpStatus.OK);
		} catch (ConditionsNotMet | TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(NOT_HANDLED, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("getCompanyCoupons")
	public ResponseEntity<?> getCompanyCoupons(@RequestHeader("token") String token) {
		try {
			CompanyService service = (CompanyService)tm.getService(token);
			List<Coupon> coupons = service.getAllCoupons();
			return new ResponseEntity<List<Coupon>>(coupons, HttpStatus.OK);
		} catch (TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("getCompanyCouponsByCategory/{category}")
	public ResponseEntity<?> getCompanyCouponsByCategory(@PathVariable Category category, @RequestHeader("token") String token) {
		try {
			CompanyService service = (CompanyService)tm.getService(token);
			List<Coupon> coupons = service.getAllCoupons(category);
			return new ResponseEntity<List<Coupon>>(coupons, HttpStatus.OK);
		} catch (TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("getCompanyCouponsByMaxPrice/{maxPrice}")
	public ResponseEntity<?> getCompanyCouponsByMaxPrice(@PathVariable double maxPrice, @RequestHeader("token") String token) {
		try {
			CompanyService service = (CompanyService)tm.getService(token);
			List<Coupon> coupons = service.getAllCoupons(maxPrice);
			return new ResponseEntity<List<Coupon>>(coupons, HttpStatus.OK);
		} catch (TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("getDetails")
	public ResponseEntity<?> getCompanyDetails(@RequestHeader("token") String token) {
		try {
			CompanyService service = (CompanyService)tm.getService(token);
			Company company = service.getCompany();
			return new ResponseEntity<Company>(company, HttpStatus.OK);
		} catch (TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
