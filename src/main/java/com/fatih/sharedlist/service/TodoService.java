package com.fatih.sharedlist.service;

import java.util.List;

import com.fatih.sharedlist.entity.Todo;

public interface TodoService {
	
	public Todo createTodo();
	public void saveTodo(Todo todo);
	public List<Todo> findByListingId(String listingId);
	public List<Todo> findByUserName(String userName);
	public List<Todo> findByListingName(String listingName);
	public Todo findByTodoId(String todoId);
	public void deletTodo(Todo todo);
	
	
	

}
