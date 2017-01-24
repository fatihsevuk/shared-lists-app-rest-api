package com.fatih.todo.model;

import com.fatih.todo.entity.User;

public class TodoDetailModel extends BaseModel{
	
	
	private String todoId; 
	private String todoContent; 
	private String userName;
	private String listingName;
	private String listingId;
	private String listingUserName;
	
	private double rateAverage;
	
	
	
	private int rate;
	
	
	
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
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getListingName() {
		return listingName;
	}
	public void setListingName(String listingName) {
		this.listingName = listingName;
	}
	public String getListingId() {
		return listingId;
	}
	public void setListingId(String listingId) {
		this.listingId = listingId;
	}
	public double getRateAverage() {
		return rateAverage;
	}
	public void setRateAverage(double rateAverage) {
		this.rateAverage = rateAverage;
	}
	
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getListingUserName() {
		return listingUserName;
	}
	public void setListingUserName(String listingUserName) {
		this.listingUserName = listingUserName;
	}
	
	
	
	
	
}
