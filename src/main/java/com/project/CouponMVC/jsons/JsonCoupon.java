package com.project.CouponMVC.jsons;

import java.time.LocalDate;

import com.project.CouponMVC.enums.Category;

public class JsonCoupon {

	private int id;
	private Category category;
	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private int amount;
	private double price;
	private String image;
	
	
	public JsonCoupon(int id, Category category, String title, String description, LocalDate startDate,
			LocalDate endDate, int amount, double price, String image) {
		super();
		this.id = id;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}
	

	public int getId() {
		return id;
	}
	public Category getCategory() {
		return category;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public int getAmount() {
		return amount;
	}
	public double getPrice() {
		return price;
	}
	public String getImage() {
		return image;
	}
 
	
	
	
}
