package com.fatih.todo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fatih.todo.entity.Listing;
import com.fatih.todo.entity.Todo;
import com.fatih.todo.entity.User;

@Repository
@Qualifier("todoRepository")
public interface TodoRepository extends MongoRepository<Todo, String>{
	public List<Todo> findByListing(Listing listing);
	public List<Todo> findByUser(User user);
	public Todo findByTodoId(String todoId);
}
