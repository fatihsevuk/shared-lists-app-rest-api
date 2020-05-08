package com.fatih.sharedlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fatih.sharedlist.entity.User;
import com.fatih.sharedlist.model.BaseModel;
import com.fatih.sharedlist.model.UserDetailModel;
import com.fatih.sharedlist.service.UserService;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public BaseModel add(@RequestBody UserDetailModel userDetailModel) {

		BaseModel baseModel = new BaseModel();

		try {
			
			User user=userService.findUserByUserName(userDetailModel.getUserName());
			
			if(user==null){
			
				if(userDetailModel.getEmail()!=null || userDetailModel.getEmail()==""){
				
				user = new User();
	
				user.setUserName(userDetailModel.getUserName());
	
				user.setPassword(userDetailModel.getPassword());
				
				user.setEmail(userDetailModel.getEmail());
				
				
	
				userService.saveUser(user);
	
				baseModel.setErrorCode(0);
				baseModel.setErrorMessage("Kullanıcı başarılı bir şekilde eklendi.");
				
				}else{
					baseModel.setErrorCode(1);
					baseModel.setErrorMessage("Email adresini girmek zorundasın.");
				}
			
			}else{
				baseModel.setErrorCode(1);
				baseModel.setErrorMessage("Bu kullanıcı adı daha önce alınmış.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			baseModel.setErrorCode(1);
			baseModel.setErrorMessage("Kullanıcı ekleme başarısız. " + e.getClass().getName() + " " + e.getMessage());
		}

		return baseModel;

	}

	@RequestMapping(value = "/user/{userName}", method = RequestMethod.GET)
	public UserDetailModel find(@PathVariable String userName) {

		return null;
	}

	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public BaseModel login(@RequestBody UserDetailModel userDetailModel) {

		BaseModel baseModel = new BaseModel();

		try {
			
			User user=userService.findUserByUserName(userDetailModel.getUserName());
			
			if(user!=null &&  user.getPassword().equals(userDetailModel.getPassword())){
			
					baseModel.setErrorCode(0);
					baseModel.setErrorMessage("Kullanıcı girişi başarılı.");
					
			}else{
				baseModel.setErrorCode(1);
				baseModel.setErrorMessage("Bu kullanıcı adı ya da parola yalnış");
			}

		} catch (Exception e) {
			e.printStackTrace();
			baseModel.setErrorCode(1);
			baseModel.setErrorMessage("Giriş başarısız. " + e.getClass().getName() + " " + e.getMessage());
		}

		return baseModel;

	}
	
	
	
	
	
}
