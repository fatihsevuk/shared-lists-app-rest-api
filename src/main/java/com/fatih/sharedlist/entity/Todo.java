package com.fatih.sharedlist.entity;

import java.util.Date;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;


public class Todo {
	
	@Id
	private String todoId;
	
	private String todoContent;
	
	private double rateAverage;
	
	private int rateCount;
	
	private int rate;
	
	@DBRef
	private User user;
	
	@DBRef
	private Listing listing;
	
	private Date createdDate;

	
	





	public String getTodoId() {
		return todoId;
	}






	public void setTodoId(String todoId) {
		this.todoId = todoId;
	}






	public String getTodoContent() {
		return todoContent;
	}






	public void setTodoContent(String todoContent) {
		this.todoContent = todoContent;
	}






	public double getRateAverage() {
		return rateAverage;
	}






	public void setRateAverage(double rateAverage) {
		this.rateAverage = rateAverage;
	}






	public int getRateCount() {
		return rateCount;
	}






	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}






	public int getRate() {
		return rate;
	}






	public void setRate(int rate) {
		this.rate = rate;
	}






	public User getUser() {
		return user;
	}






	public void setUser(User user) {
		this.user = user;
	}






	public Listing getListing() {
		return listing;
	}






	public void setListing(Listing listing) {
		this.listing = listing;
	}






	public Date getCreatedDate() {
		return createdDate;
	}






	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}






	public Todo(String todoContent, double rateAverage, int rateCount, int rate, User user, Listing listing,Date createdDate) {
		
		this.todoContent = todoContent;
		this.rateAverage = rateAverage;
		this.rateCount = rateCount;
		this.rate = rate;
		this.user = user;
		this.listing = listing;
		this.createdDate = createdDate;
	}






	public Todo() {
		// TODO Auto-generated constructor stub
	}
	

}
