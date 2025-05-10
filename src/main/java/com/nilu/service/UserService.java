package com.nilu.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.nilu.entity.User;

public interface UserService {

	public void saveNew(User user);
	
	public void saveNewAdmin(User user);
	
	public void save(User user);

	public List<User> findAll();

	public Optional<User> findById(ObjectId Id);

	public void deleteByUserName(String userName);

	public User findByName(String name);
}
