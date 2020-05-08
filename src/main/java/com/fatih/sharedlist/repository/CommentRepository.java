package com.fatih.sharedlist.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fatih.sharedlist.entity.Comment;
import com.fatih.sharedlist.entity.Todo;
import com.fatih.sharedlist.entity.User;

@Repository
@Qualifier("commentRepository")
public interface CommentRepository extends MongoRepository<Comment, String>{
	
	public List<Comment> findByTodo(Todo todo);
	public List<Comment> findByUser(User user);
	public Comment findByCommentId(String commentId);

}
