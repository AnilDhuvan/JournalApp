package com.nilu.repositery;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.nilu.entity.User;

public interface UserRepo extends MongoRepository<User, ObjectId>{

	User findByName(String name);
	
	void deleteByName(String name);
}
