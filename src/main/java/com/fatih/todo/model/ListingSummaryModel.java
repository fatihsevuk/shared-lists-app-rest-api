package com.fatih.todo.model;

import java.util.List;

public class ListingSummaryModel extends BaseModel{
	
	
	private List<ListingDetailModel> listingDetailList;

	


	public List<ListingDetailModel> getListingDetailList() {
		return listingDetailList;
	}

	public void setListingDetailList(List<ListingDetailModel> listingDetailList) {
		this.listingDetailList = listingDetailList;
	}

	
	
	
	
	
	

}
