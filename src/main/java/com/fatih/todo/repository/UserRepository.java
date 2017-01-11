package com.fatih.todo.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fatih.todo.entity.User;

@Repository
@Qualifier("userRepository")
public interface UserRepository extends MongoRepository<User, String>{
	public User findByUserName(String userName);

}
