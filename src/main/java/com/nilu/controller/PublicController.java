package com.nilu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nilu.entity.User;
import com.nilu.service.UserServiceImpl;

@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private UserServiceImpl service;


	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody User user) {
		User exitsUserOrNot = service.findByName(user.getName());
		if(exitsUserOrNot != null) {
			return new ResponseEntity<>("Sorry this name is Already exits",HttpStatus.NOT_EXTENDED);
		}
		service.saveNew(user);
		return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("/health-check")
	public String healthCheck() {
		return "OK";
	}

}
