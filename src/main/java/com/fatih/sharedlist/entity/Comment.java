package com.fatih.sharedlist.entity;

import java.util.Date;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;


public class Comment {
	
	@Id
	private String commentId;
	
	private String commentContent;
	
	
	@DBRef
	private Todo todo;
	
	@DBRef
	private User user;
	
	
	private Date createdDate;

	

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Comment(String commentContent, Todo todo, User user, Date createdDate) {
		
		this.commentContent = commentContent;
		this.todo = todo;
		this.user = user;
		this.createdDate = createdDate;
	}
	
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
