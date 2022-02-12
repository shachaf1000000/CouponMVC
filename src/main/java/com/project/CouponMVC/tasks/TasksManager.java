package com.project.CouponMVC.tasks;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.CouponMVC.entities.Company;
import com.project.CouponMVC.entities.Coupon;
import com.project.CouponMVC.repositories.CompanyRepository;
import com.project.CouponMVC.repositories.CouponRepository;
import com.project.CouponMVC.repositories.CustomerRepository;
import com.project.CouponMVC.tokens.TokenManager;
import com.project.CouponMVC.tokens.TokenManager.Token;

@Component
public class TasksManager {

	private static final int SECOND_TIME = 1000;
	private static final int MINUTE_TIME = SECOND_TIME * 60;
	private static final int HOUR_TIME = MINUTE_TIME * 60;
	private static final long DAY_TIME = HOUR_TIME * 24;
	private static final long WEEK_TIME = DAY_TIME * 7;
	
	@Autowired
	private CouponRepository couRep;
	@Autowired
	private CompanyRepository coRep;
	@Autowired
	private CustomerRepository cuRep;
	@Autowired
	private TokenManager tkm;
	
	@Scheduled(fixedRate = DAY_TIME)
	public void deleteExpiredCouponsTask() {
		
		int couponCount = 0;
		List<Coupon> coupons = couRep.getAllCoupons();
		for (Coupon coupon : coupons) {
			if(coupon.isExpired()) {
				couRep.delete(coupon);
				couponCount++;
			}
		}
		System.out.println(couponCount!=0 ? "✔ "+ couponCount +" expired coupons deleted." 
										  : "✔ No expired coupons found.");
	}
	
	// Floating coupons = coupons where the company ID does not belong to any existing company.
	@Scheduled(fixedRate = WEEK_TIME)
	public void deleteFloatingCouponsTask() {
		
		int couponCount = 0;
		
		List<Coupon> coupons = couRep.getAllCoupons();
		
		for (Coupon coupon : coupons) {
			
			Company company = coRep.getCompany(coupon.getCompany().getId());
			
			if(company==null) {
				couRep.delete(coupon);
				couponCount++;
			}
		}
		System.out.println(couponCount!=0 ? "✔ "+ couponCount +" floating coupons deleted." 
										  : "✔ No floating coupons found.");
	}
	
	@Scheduled(fixedRate = SECOND_TIME*10)
	public void deleteExpiredTokensTask() {
		Set<Token> deletedTokens = tkm.deleteExpiredCoupons();
		
		System.out.println(deletedTokens.size()>0 ?
				"Removed expired tokens:\n" + deletedTokens  : "");
		
	}
	
	
	
}
