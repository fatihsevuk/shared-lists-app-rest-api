package com.fatih.sharedlist.service;

import java.util.List;

import com.fatih.sharedlist.entity.Listing;

public interface ListingService{
	
	public Listing createListing();
	public void saveListing(Listing listing);
	public List<Listing> findByPrivateList(boolean privateList);
	public List<Listing> findByUserName(String userName);
	public Listing findByListingId(String listingId);
	public Listing findByListingName(String listingName);
	public void deleteList(Listing listing);
	

}
