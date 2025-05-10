package com.nilu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.nilu.entity.User;
import com.nilu.repositery.UserRepo;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByName(username);
			//	.orElseThrow(()-> new UsernameNotFoundException("username not found: "+username));
		
		if(user != null) {
			UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
			.username(user.getName())
			.password(user.getPassword())
			.roles(user.getRoles().toArray(new String[0]))
			.build();
			
			return userDetails;
		}
		
		 throw new UsernameNotFoundException("user name not found with username: " +username);
	}

}
