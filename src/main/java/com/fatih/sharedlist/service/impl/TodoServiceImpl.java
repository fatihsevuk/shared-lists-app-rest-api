package com.fatih.sharedlist.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatih.sharedlist.entity.Listing;
import com.fatih.sharedlist.entity.Todo;
import com.fatih.sharedlist.entity.User;
import com.fatih.sharedlist.repository.ListingRepository;
import com.fatih.sharedlist.repository.TodoRepository;
import com.fatih.sharedlist.repository.UserRepository;
import com.fatih.sharedlist.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService{

	
	private TodoRepository todoRepository;
	private ListingRepository listingRepository;
	private UserRepository userRepository;
	
	@Autowired
	public TodoServiceImpl(TodoRepository todoRepository , ListingRepository listingRepository , UserRepository userRepository) {
		this.todoRepository=todoRepository;
		this.listingRepository=listingRepository;
		this.userRepository=userRepository;
	}

	@Override
	public Todo createTodo() {
		Todo todo=new Todo();
		return todo;
	}

	@Override
	public void saveTodo(Todo todo) {
		todoRepository.save(todo);
		
	}

	@Override
	public List<Todo> findByListingId(String listingId) {
		
		Listing listing=listingRepository.findOne(listingId); 
		
		 List<Todo> todoList=todoRepository.findByListing(listing);
		
		return todoList;
	}

	@Override
	public List<Todo> findByUserName(String userName) {
		
		User user=userRepository.findByUserName(userName);
		
		
		List<Todo>  todoList=todoRepository.findByUser(user);
		
		return todoList;
	}

	@Override
	public List<Todo> findByListingName(String listingName) {
		
		Listing listing=listingRepository.findByListingName(listingName);
		
		List<Todo>  todoList=todoRepository.findByListing(listing);
		
		
		return todoList;
	}

	@Override
	public Todo findByTodoId(String todoId) {
		
		return todoRepository.findByTodoId(todoId);
	}

	@Override
	public void deletTodo(Todo todo) {
		
		todoRepository.delete(todo);
		
	}


	
	
	
}
