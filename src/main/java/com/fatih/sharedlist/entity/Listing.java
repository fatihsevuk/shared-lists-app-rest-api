package com.fatih.sharedlist.entity;

import java.util.Date;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;


public class Listing{
	
	@Id
	private String listingId;
	
	private String listingName;
	
	private String listingDesc;
	
	@DBRef
	private User user;
	
	private boolean privateList;
	
	private Date createdDate;

	
	
	

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isPrivateList() {
		return privateList;
	}

	public void setPrivateList(boolean privateList) {
		this.privateList = privateList;
	}

	
	
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	

	public String getListingDesc() {
		return listingDesc;
	}

	public void setListingDesc(String listingDesc) {
		this.listingDesc = listingDesc;
	}

	

	public Listing(String listingName, String listingDesc, User user, boolean privateList, Date createdDate) {
		super();
		this.listingName = listingName;
		this.listingDesc = listingDesc;
		this.user = user;
		this.privateList = privateList;
		this.createdDate = createdDate;
	}

	public Listing() {
		// TODO Auto-generated constructor stub
	}
	
	

}
