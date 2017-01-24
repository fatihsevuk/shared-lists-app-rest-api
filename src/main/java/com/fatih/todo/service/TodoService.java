package com.fatih.todo.service;

import java.util.List;

import com.fatih.todo.entity.Listing;
import com.fatih.todo.entity.Todo;
import com.fatih.todo.entity.User;

public interface TodoService {
	
	public Todo createTodo();
	public void saveTodo(Todo todo);
	public List<Todo> findByListingId(String listingId);
	public List<Todo> findByUserName(String userName);
	public List<Todo> findByListingName(String listingName);
	public Todo findByTodoId(String todoId);
	public void deletTodo(Todo todo);
	
	
	

}
