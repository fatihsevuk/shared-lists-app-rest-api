package com.fatih.todo.model;

import java.util.List;

import com.fatih.todo.entity.User;



public class ListingDetailModel extends BaseModel {
	
	
	
	private String listingId; 
	private String listingName; 
	private String userName;
	private String listingDesc;
	private boolean privateList;
	private List<TodoDetailModel> todoDetailList;
	
	
	
	public String getListingId() {
		return listingId;
	}
	public void setListingId(String listingId) {
		this.listingId = listingId;
	}
	public String getListingName() {
		return listingName;
	}
	public void setListingName(String listingName) {
		this.listingName = listingName;
	}
	
	
	
	
	
	public boolean isPrivateList() {
		return privateList;
	}
	public void setPrivateList(boolean privateList) {
		this.privateList = privateList;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<TodoDetailModel> getTodoDetailList() {
		return todoDetailList;
	}
	public void setTodoDetailList(List<TodoDetailModel> todoDetailList) {
		this.todoDetailList = todoDetailList;
	}
	public String getListingDesc() {
		return listingDesc;
	}
	public void setListingDesc(String listingDesc) {
		this.listingDesc = listingDesc;
	}
	
	
	
	
	
	
	
	

}
