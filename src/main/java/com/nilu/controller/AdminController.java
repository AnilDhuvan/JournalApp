package com.nilu.controller;

import java.util.List;

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
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping("/all-users")
	public ResponseEntity<?> getAllUsers(){
		List<User> all = userService.findAll();
		if(all != null && !all.isEmpty()) {
			return new ResponseEntity<>(all,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/create-new-admin")
	public void createAdmin(@RequestBody User user){
				userService.saveNewAdmin(user);
		
	}

}
