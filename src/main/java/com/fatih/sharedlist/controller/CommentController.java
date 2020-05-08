package com.fatih.sharedlist.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fatih.sharedlist.entity.Comment;
import com.fatih.sharedlist.entity.Todo;
import com.fatih.sharedlist.entity.User;
import com.fatih.sharedlist.model.BaseModel;
import com.fatih.sharedlist.model.CommentDetailModel;
import com.fatih.sharedlist.model.CommentSummaryModel;
import com.fatih.sharedlist.service.CommentService;
import com.fatih.sharedlist.service.ListingService;
import com.fatih.sharedlist.service.TodoService;
import com.fatih.sharedlist.service.UserService;
import com.fatih.sharedlist.service.impl.CommentServiceImpl;
import com.fatih.sharedlist.service.impl.TodoServiceImpl;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
public class CommentController{
	
	
	private TodoService todoService;
	private UserService userService;
	private ListingService listingService;
	private CommentService commentService;
	
	
	@Autowired
	public CommentController(CommentServiceImpl commentService , TodoServiceImpl todoService, UserService userService, ListingService listingService) {
		
		this.commentService=commentService;
		this.todoService = todoService;
		this.userService = userService;
		this.listingService = listingService;
	}
	
	
	@RequestMapping(value = "/comment", method = RequestMethod.PUT)
	public BaseModel add(@RequestBody CommentDetailModel commentDetailModel ){
		
		
		BaseModel baseModel = new BaseModel();

		try {

			Comment comment = commentService.createComment();

			comment.setCommentContent(commentDetailModel.getCommentContent());

			User user = userService.findUserByUserName(commentDetailModel.getUserName());
			comment.setUser(user);
			
			Todo todo=todoService.findByTodoId(commentDetailModel.getTodoId());
			comment.setTodo(todo);
						
			
			
			commentService.saveComment(comment);

			baseModel.setErrorCode(0);
			baseModel.setErrorMessage("Yorum başarıyla eklendi.");

		} catch (Exception e) {
			e.printStackTrace();
			baseModel.setErrorCode(1);
			baseModel.setErrorMessage("Yalnışlık! " + e.getClass().getName() + " " + e.getMessage());

		}
		return baseModel;
		
		
		
		
	}
	
	
	@RequestMapping(value = "/comments1/{userName}", method = RequestMethod.GET)
	public CommentSummaryModel findByUserName(@PathVariable String userName){

		CommentSummaryModel commentSummaryModel = new CommentSummaryModel();
		commentSummaryModel.setCommentDetailList(new ArrayList<>());
		
		try{

			

			List<Comment> comments = commentService.findByUserName(userName);

			for (Comment comment : comments) {
				
				
				
				CommentDetailModel commentDetailModel = new CommentDetailModel();
				
				commentDetailModel.setCommentContent(comment.getCommentContent());
				commentDetailModel.setCommentId(comment.getCommentId());
				

				

				if (comment.getUser() != null) {
					commentDetailModel.setUserName(comment.getUser().getUserName());
					
				}
				
				
				if(comment.getTodo()!=null){
					commentDetailModel.setTodoId(comment.getTodo().getTodoId());
					commentDetailModel.setTodoContent(comment.getTodo().getTodoContent());
					commentDetailModel.setListingName(comment.getTodo().getListing().getListingName());
				}
				
				commentDetailModel.setErrorCode(0);
				commentDetailModel.setErrorMessage("Oldu");

				commentSummaryModel.getCommentDetailList().add(commentDetailModel);
			}

			commentSummaryModel.setErrorCode(0);
			commentSummaryModel.setErrorMessage("Listeleme Başarılı");

		} catch (Exception e) {
			e.printStackTrace();
			
			commentSummaryModel.setErrorCode(1);
			commentSummaryModel.setErrorMessage("Yalnışlık" + e.getClass().getName() + " " + e.getMessage());
		}
		return commentSummaryModel;

	}
	
	
	@RequestMapping(value = "/comments2/{todoId}", method = RequestMethod.GET)
	public CommentSummaryModel findByTodoId(@PathVariable String todoId) {

		CommentSummaryModel commentSummaryModel = new CommentSummaryModel();

		try {

			commentSummaryModel.setCommentDetailList(new ArrayList<>());

			List<Comment> comments = commentService.findByTodoId(todoId);

			for (Comment comment : comments) {

				CommentDetailModel commentDetailModel = new CommentDetailModel();

				commentDetailModel.setCommentContent(comment.getCommentContent());
				commentDetailModel.setCommentId(comment.getCommentId());
				

				

				if (comment.getUser() != null) {
					commentDetailModel.setUserName(comment.getUser().getUserName());
				}
				
				if(comment.getTodo()!=null){
					commentDetailModel.setTodoContent(comment.getTodo().getTodoContent());
					commentDetailModel.setTodoId(comment.getTodo().getTodoId());
				}

				commentSummaryModel.getCommentDetailList().add(commentDetailModel);
			}

			commentSummaryModel.setErrorCode(0);
			commentSummaryModel.setErrorMessage("Oldu");

		} catch (Exception e) {
			e.printStackTrace();
			commentSummaryModel.setErrorCode(1);
			commentSummaryModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}
		return commentSummaryModel;

	}


	@RequestMapping(value = "/comment/{commentId}", method = RequestMethod.GET)
	public CommentDetailModel findByCommentId(@PathVariable String commentId) {
		
		CommentDetailModel commentDetailModel=new CommentDetailModel();
		
	

		try{
			
			Comment comment = commentService.findByCommentId(commentId);
			
			
		
			commentDetailModel.setCommentContent(comment.getCommentContent());
			commentDetailModel.setCommentId(comment.getCommentId());
			
			
			if (comment.getUser() != null) {
				commentDetailModel.setUserName(comment.getUser().getUserName());
			}
			
			
			if(comment.getTodo() != null){
				
				commentDetailModel.setTodoContent(comment.getTodo().getTodoContent());
				commentDetailModel.setTodoId(comment.getTodo().getTodoId());
			}

			

		

			commentDetailModel.setErrorCode(0);
			commentDetailModel.setErrorMessage("Oldu");

		} catch (Exception e) {
			e.printStackTrace();
			commentDetailModel.setErrorCode(1);
			commentDetailModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}

		return commentDetailModel;

	}
	
	@RequestMapping(value="/deleteComment/{commentId}",method=RequestMethod.DELETE)
	public BaseModel delete(@PathVariable String commentId){
	
		BaseModel baseModel=new BaseModel();
		
		try{
			
			Comment comment=commentService.findByCommentId(commentId);
			commentService.deleteComment(comment);
			
			baseModel.setErrorCode(0);
			baseModel.setErrorMessage("Oldu");
			
		}catch(Exception e){
			e.printStackTrace();
			baseModel.setErrorCode(1);
			baseModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}
		
		
		return baseModel;
	}
	
	

}
