package com.fatih.todo.service;

import java.util.List;

import com.fatih.todo.entity.Comment;

public interface CommentService {
	
	
	public Comment createComment();
	public void saveComment(Comment comment);
	public List<Comment> findByTodoId(String todoId);
	public List<Comment> findByUserName(String userName);
	public Comment findByCommentId(String commentId);
	public void deleteComment(Comment comment);
	

}
