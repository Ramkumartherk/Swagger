package com.pack.swagger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pack.swagger.Repository.StudentRepo;
@SpringBootApplication
public class SwaggerApplication{
	@Autowired
	StudentRepo studentRepo;

	public static void main(String[] args) {
		SpringApplication.run(SwaggerApplication.class, args);
	}



}
