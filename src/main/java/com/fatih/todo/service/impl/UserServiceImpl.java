package com.fatih.todo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatih.todo.entity.User;
import com.fatih.todo.repository.UserRepository;
import com.fatih.todo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository=userRepository;
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
		
	}

	

	@Override
	public User findUserByUserName(String userName){
		User user=userRepository.findByUserName(userName);
		return user;
	}

	@Override
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
		
	}

	@Override
	public User updateUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	

}
