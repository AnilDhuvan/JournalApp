package com.nilu.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.nilu.entity.JournalEntity;
import com.nilu.entity.User;

public interface JournalService {

	public void save(JournalEntity journalEntity, User user);
	
	public void save(JournalEntity journalEntity);

	public List<JournalEntity> findAll();

	public Optional<JournalEntity> findById(ObjectId Id);

	public boolean deleteById(ObjectId Id, String userName);

}
