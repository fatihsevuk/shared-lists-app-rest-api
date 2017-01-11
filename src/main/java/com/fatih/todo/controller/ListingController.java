package com.fatih.todo.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fatih.todo.entity.Comment;
import com.fatih.todo.entity.Listing;
import com.fatih.todo.entity.Todo;
import com.fatih.todo.entity.User;
import com.fatih.todo.model.BaseModel;
import com.fatih.todo.model.ListingDetailModel;
import com.fatih.todo.model.ListingSummaryModel;
import com.fatih.todo.service.CommentService;
import com.fatih.todo.service.ListingService;
import com.fatih.todo.service.TodoService;
import com.fatih.todo.service.UserService;
import com.fatih.todo.service.impl.TodoServiceImpl;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class ListingController {

	private ListingService listingService;
	private UserService userService;
	private TodoService todoService;
	private CommentService commentService;
	
	@Autowired
	public ListingController(TodoServiceImpl todoService , CommentService commentService, ListingService listingService, UserService userService) {
		this.todoService = todoService;
		this.listingService = listingService;
		this.userService = userService;
		this.commentService=commentService;
	}

	@RequestMapping(value = "/listing", method = RequestMethod.PUT)
	public BaseModel add(@RequestBody ListingDetailModel listingDetailModel) {

		System.out.println("Listing Name: " + listingDetailModel.getListingName());

		BaseModel baseModel = new BaseModel();

		try {
			
			if(listingDetailModel.getListingName()!=null && listingDetailModel.getListingName()!=""){
			
			Listing listing = listingService.createListing();
			listing.setListingName(listingDetailModel.getListingName());
			listing.setPrivateList(listingDetailModel.isPrivateList());
			listing.setListingDesc(listingDetailModel.getListingDesc());

			User user = userService.findUserByUserName(listingDetailModel.getUserName());

			listing.setUser(user);

			listingService.saveListing(listing);

			baseModel.setErrorCode(0);
			baseModel.setErrorMessage("Liste başarıyla eklendi"); 
			
			}else{
				baseModel.setErrorCode(1);
				baseModel.setErrorMessage("Liste adını girmek zorundasın ");

			}
		} catch (Exception e) {
			e.printStackTrace();
			baseModel.setErrorCode(1);
			baseModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());

		}
		return baseModel;
	}

	

	
	@RequestMapping(value = "/publiclistings", method = RequestMethod.GET)
	public ListingSummaryModel findByPrivate(){

		ListingSummaryModel listingSummaryModel = new ListingSummaryModel();

		try {
			listingSummaryModel.setListingDetailList(new ArrayList<>());

			List<Listing> listings = listingService.findByPrivateList(false);

			for (Listing listing : listings) {

				ListingDetailModel listingDetail = new ListingDetailModel();

				listingDetail.setListingId(listing.getListingId());
				listingDetail.setListingName(listing.getListingName());
				listingDetail.setListingDesc(listing.getListingDesc());

				if (listing.getUser() != null) {
					listingDetail.setUserName(listing.getUser().getUserName());
				}

				listingSummaryModel.getListingDetailList().add(listingDetail);
			}

			listingSummaryModel.setErrorCode(0);
			listingSummaryModel.setErrorMessage("Oldu");

		} catch (Exception e) {
			e.printStackTrace();
			listingSummaryModel.setErrorCode(1);
			listingSummaryModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}

		return listingSummaryModel;

	}

	
	
	
	
	@RequestMapping(value = "/listing/{listingId}", method = RequestMethod.GET)
	public ListingDetailModel findByListingId(@PathVariable String listingId) {

		ListingDetailModel listingDetailModel = new ListingDetailModel();

		try {
			Listing listing = listingService.findByListingId(listingId);

			listingDetailModel.setListingId(listing.getListingId());
			listingDetailModel.setListingName(listing.getListingName());
			listingDetailModel.setListingDesc(listing.getListingDesc());

			if (listing.getUser() != null) {
				listingDetailModel.setUserName(listing.getUser().getUserName());
			}
			
			listingDetailModel.setErrorCode(1);
			listingDetailModel.setErrorMessage("Oldu");

		} catch (Exception e) {
			e.printStackTrace();
			listingDetailModel.setErrorCode(1);
			listingDetailModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}

		return listingDetailModel;

	}

	@RequestMapping(value = "/userlistings/{userName}", method = RequestMethod.GET)
	public ListingSummaryModel findByUserName(@PathVariable String userName) {

		ListingSummaryModel listingSummaryModel = new ListingSummaryModel();

		try {

			listingSummaryModel.setListingDetailList(new ArrayList<>());
			List<Listing> listings = listingService.findByUserName(userName);

			for (Listing listing : listings) {

				ListingDetailModel listingDetail = new ListingDetailModel();

				System.out.println(listing.getListingName());

				listingDetail.setListingId(listing.getListingId());
				listingDetail.setListingName(listing.getListingName());
				listingDetail.setListingDesc(listing.getListingDesc());
				listingDetail.setPrivateList(listing.isPrivateList());

				if (listing.getUser() != null) {
					listingDetail.setUserName(listing.getUser().getUserName());
				}

				listingSummaryModel.getListingDetailList().add(listingDetail);

			}

			listingSummaryModel.setErrorCode(0);
			listingSummaryModel.setErrorMessage("Oldu");

		} catch (Exception e) {
			e.printStackTrace();
			listingSummaryModel.setErrorCode(1);
			listingSummaryModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}

		return listingSummaryModel;

	}

	
	
	@RequestMapping(value = "/userprivatelistings/{userName}", method = RequestMethod.GET)
	public ListingSummaryModel findByUserPrivate(@PathVariable String userName) {

		ListingSummaryModel listingSummaryModel = new ListingSummaryModel();

		try {

			listingSummaryModel.setListingDetailList(new ArrayList<>());
			List<Listing> listings = listingService.findByUserName(userName);

			for (Listing listing : listings) {

				ListingDetailModel listingDetail = new ListingDetailModel();

				System.out.println(listing.getListingName());
				
				if(listing.isPrivateList()==true){
				
				listingDetail.setListingId(listing.getListingId());
				listingDetail.setListingName(listing.getListingName());
				listingDetail.setListingDesc(listing.getListingDesc());
				listingDetail.setPrivateList(listing.isPrivateList());

				if (listing.getUser() != null) {
					listingDetail.setUserName(listing.getUser().getUserName());
				}
				listingSummaryModel.getListingDetailList().add(listingDetail);
				}

				

			}

			listingSummaryModel.setErrorCode(0);
			listingSummaryModel.setErrorMessage("Oldu");

		} catch (Exception e) {
			e.printStackTrace();
			listingSummaryModel.setErrorCode(1);
			listingSummaryModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}

		return listingSummaryModel;

	}
	
	
	@RequestMapping(value = "/userpubliclistings/{userName}", method = RequestMethod.GET)
	public ListingSummaryModel findByUserPublic(@PathVariable String userName) {

		ListingSummaryModel listingSummaryModel = new ListingSummaryModel();

		try {

			listingSummaryModel.setListingDetailList(new ArrayList<>());
			List<Listing> listings = listingService.findByUserName(userName);

			for (Listing listing : listings) {

				ListingDetailModel listingDetail = new ListingDetailModel();

				System.out.println(listing.getListingName());
				
				if(listing.isPrivateList()==false){
				
				listingDetail.setListingId(listing.getListingId());
				listingDetail.setListingName(listing.getListingName());
				listingDetail.setListingDesc(listing.getListingDesc());
				listingDetail.setPrivateList(listing.isPrivateList());

				if (listing.getUser() != null) {
					listingDetail.setUserName(listing.getUser().getUserName());
				}
				listingSummaryModel.getListingDetailList().add(listingDetail);
				}

				

			}

			listingSummaryModel.setErrorCode(0);
			listingSummaryModel.setErrorMessage("Oldu");

		} catch (Exception e) {
			e.printStackTrace();
			listingSummaryModel.setErrorCode(1);
			listingSummaryModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}

		return listingSummaryModel;

	}
	
	
	@RequestMapping(value = "/listing/{listingId}", method = RequestMethod.DELETE)
	public BaseModel delete(@PathVariable String listingId) {

		BaseModel baseModel = new BaseModel();

		try {
			
			List<Todo> todos = todoService.findByListingId(listingId);
			
			for (Todo todo : todos) {
				
				List<Comment> comments=commentService.findByTodoId(todo.getTodoId()); 
				
				for (Comment comment : comments) {
					commentService.deleteComment(comment);
				}
				
				todoService.deletTodo(todo);
				
				
			}
			
			Listing listing = listingService.findByListingId(listingId);
			listingService.deleteList(listing);

			baseModel.setErrorCode(0);
			baseModel.setErrorMessage("Oldu");

		} catch (Exception e) {
			e.printStackTrace();
			baseModel.setErrorCode(1);
			baseModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}

		return baseModel;
	}

	@RequestMapping(value = "/listing/{listingId}", method = RequestMethod.POST)
	public BaseModel update(@RequestBody ListingDetailModel listingDetailModel , @PathVariable String listingId) {
		BaseModel baseModel = new BaseModel();

	

		try {
			Listing listing = listingService.findByListingId(listingId);

			if (listing != null){
				listing.setListingName(listingDetailModel.getListingName());
				listing.setPrivateList(listingDetailModel.isPrivateList());
				listing.setListingDesc(listing.getListingDesc());
				
				
				
				
				listingService.saveListing(listing);
				baseModel.setErrorCode(0);
				baseModel.setErrorMessage("Oldu");

			} else{
				baseModel.setErrorCode(1);
				baseModel.setErrorMessage("Bu Id de liste yok");
			}

		} catch (Exception e) {
			e.printStackTrace();

			baseModel.setErrorCode(1);
			baseModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}

		return baseModel;
	}

}
