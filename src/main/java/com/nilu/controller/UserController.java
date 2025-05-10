package com.nilu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nilu.entity.User;
import com.nilu.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl service;
	
	

	

	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		
		// to spring Security
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		
		User exitsUser = service.findByName(name);

		if (exitsUser == null) {
			return new ResponseEntity<>("User not found", HttpStatus.OK);
		}
		exitsUser.setName(user.getName());
		exitsUser.setEmail(user.getEmail());
		exitsUser.setPassword(user.getPassword());

		service.saveNew(exitsUser);  // same id pr save ho jayega

		return new ResponseEntity<>("User updated successfully", HttpStatus.OK);

	}

	
     @DeleteMapping
	public ResponseEntity<String> deleteUser(@RequestBody User user) {
		
		// to spring Security
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		
		 service.deleteByUserName(name);
		return new ResponseEntity<>( HttpStatus.OK);

	}

	
	
	
	
//	@GetMapping
//	public ResponseEntity<?> getAllUsers() {
//		List<User> users = service.findAll();
//
//		if (users.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//
//		return new ResponseEntity<>(users, HttpStatus.OK);
//	}
	
}
