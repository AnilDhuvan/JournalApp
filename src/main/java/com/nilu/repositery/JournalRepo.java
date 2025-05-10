package com.nilu.repositery;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.nilu.entity.JournalEntity;

public interface JournalRepo extends MongoRepository<JournalEntity, ObjectId>{

}
