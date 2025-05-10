package com.nilu.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nilu.entity.User;
import com.nilu.repositery.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo repo;

	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


	@Override
	public void saveNew(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER"));
		 repo.save(user);
	}
	
	@Override
	public void saveNewAdmin(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER","Admin"));
		 repo.save(user);
	}
	
	public void save(User user) {
		repo.save(user);
	}


	@Override
	public List<User> findAll() {
		return repo.findAll();
	}


	@Override
	public Optional<User> findById(ObjectId Id) {
		return repo.findById(Id);
	}





	@Override
	public void deleteByUserName(String userName) {
		repo.deleteByName(userName);
	}


	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}



}
