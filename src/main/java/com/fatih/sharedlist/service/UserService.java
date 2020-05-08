package com.fatih.sharedlist.service;

import java.util.List;

import com.fatih.sharedlist.entity.User;

public interface UserService {
	
	public void saveUser(User user); 
	public User findUserByUserName(String userName);
	public List<User> getAllUser();
	public void deleteUser(User user);
	public User updateUser(String userName);
	
	

}
