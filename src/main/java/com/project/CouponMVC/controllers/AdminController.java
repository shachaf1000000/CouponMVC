package com.project.CouponMVC.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.CouponMVC.entities.Company;
import com.project.CouponMVC.entities.Customer;
import com.project.CouponMVC.exceptions.ConditionsNotMet;
import com.project.CouponMVC.exceptions.TokenDoesNotExist;
import com.project.CouponMVC.services.AdminService;

@RestController
@RequestMapping("admin")
public class AdminController extends ClientController{
	
	@GetMapping("getCompany/{companyId}")
	public ResponseEntity<?> getCompany(@PathVariable int companyId, @RequestHeader("token") String token){
		try {
			AdminService service = (AdminService) tm.getService(token);
			Company company = service.getCompany(companyId);
			return new ResponseEntity<Company>(company, HttpStatus.OK);
		}catch (TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("getCustomer/{customerId}")
	public ResponseEntity<?> getCustomer(@PathVariable int customerId, @RequestHeader("token") String token){
		try {
			AdminService service = (AdminService) tm.getService(token);
			Customer customer = service.getCustomer(customerId);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}catch (TokenDoesNotExist e) {
			return new ResponseEntity<String>(NOT_HANDLED, HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PostMapping("addCompany")
	public ResponseEntity<?> AddCompany(@RequestBody Company company, @RequestHeader("token") String token) {
		try {
			AdminService service = (AdminService) tm.getService(token);
			service.addCompany(company);
			return new ResponseEntity<String>("Company added successfully", HttpStatus.OK);
		}catch (ConditionsNotMet | TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PostMapping("addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer, @RequestHeader("token") String token){
		try {
			System.err.println(customer);
			AdminService service = (AdminService) tm.getService(token);
			service.addCustomer(customer);
			return new ResponseEntity<String>("Customer added successfully", HttpStatus.OK);
		}catch (ConditionsNotMet | TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PostMapping("updateCompany")
	public ResponseEntity<?> updateCompany(@RequestBody Company company, @RequestHeader("token") String token){
		try {
			AdminService service = (AdminService) tm.getService(token);
			service.updateCompany(company);
			return new ResponseEntity<String>("Company updated successfully", HttpStatus.OK);
		}catch (ConditionsNotMet | TokenDoesNotExist e) {
			return new ResponseEntity<String>(NOT_HANDLED, HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PostMapping("updateCustomer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @RequestHeader("token") String token){
		try {
			AdminService service = (AdminService) tm.getService(token);
			service.updateCustomer(customer);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}catch (ConditionsNotMet | TokenDoesNotExist e) {
			return new ResponseEntity<String>(NOT_HANDLED, HttpStatus.BAD_REQUEST);
		}	
	}
	
	@DeleteMapping("deleteCompany/{companyId}")
	public ResponseEntity<?> deleteCompany(@PathVariable int companyId, @RequestHeader("token") String token){
		try {
			AdminService service = (AdminService) tm.getService(token);
			service.deleteCompany(companyId);
			return new ResponseEntity<String>("Company deleted successfully", HttpStatus.OK);
		}catch (ConditionsNotMet | TokenDoesNotExist e) {
			return new ResponseEntity<String>(NOT_HANDLED, HttpStatus.BAD_REQUEST);
		}	
	}
	
	@DeleteMapping("deleteCustomer/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerId, @RequestHeader("token") String token){
		try {
			AdminService service = (AdminService) tm.getService(token);
			service.deleteCustomer(customerId);
			return new ResponseEntity<String>("Customer deleted successfully", HttpStatus.OK);
		}catch (ConditionsNotMet | TokenDoesNotExist e) {
			return new ResponseEntity<String>(NOT_HANDLED, HttpStatus.BAD_REQUEST);
		}	
	}
	
	@GetMapping("getCompanies")
	public ResponseEntity<?> getCompanies(@RequestHeader("token") String token){
		try {
			AdminService service = (AdminService) tm.getService(token);
			List<Company> companies = service.getAllCompanies();
			return new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
		}catch (TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}	
	}
	
	@GetMapping("getCustomers")
	public ResponseEntity<?> getCustomers(@RequestHeader("token") String token){
		try {
			AdminService service = (AdminService) tm.getService(token);
			List<Customer> customers = service.getAllCustomers();
			return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		}catch (TokenDoesNotExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}	
	}
	
}
