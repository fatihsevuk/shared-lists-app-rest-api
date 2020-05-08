package com.fatih.sharedlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= "com.fatih.sharedlist")
public class SpringToDoApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringToDoApp1Application.class, args);
	}
}
