package com.nilu.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nilu.entity.JournalEntity;
import com.nilu.entity.User;
import com.nilu.repositery.JournalRepo;
import com.nilu.repositery.UserRepo;


@Service
public class JournalServiceImpl implements JournalService{

	@Autowired
	private JournalRepo  jaurnalRepo;
	
	
	@Autowired
    private UserServiceImpl userService;

	@Transactional
	public void save(JournalEntity journalEntity,User user) {
		try {
		journalEntity.setDate(LocalDateTime.now());
     JournalEntity save = jaurnalRepo.save(journalEntity);
     user.getJournalEntity().add(save);
     userService.save(user);
		}catch(Exception e) {
			throw new RuntimeException("a error occurred while saving the Entry J"
					+ "ounnal serviceimpl save transactional"+e);
		}
	}
	
	public void save(JournalEntity journalEntity) {
		jaurnalRepo.save(journalEntity);
	}


	@Override
	public List<JournalEntity> findAll() {
		return jaurnalRepo.findAll();
	}


	@Override
	public Optional<JournalEntity> findById(ObjectId myid) {
		 return jaurnalRepo.findById(myid);

	}


	@Transactional
	public boolean deleteById(ObjectId Id, String userName) {
		boolean removed = false;
		try {
		User user = userService.findByName(userName);
		boolean removeIf = user.getJournalEntity().removeIf(x -> x.getId().equals(Id));
		if(removeIf) {
		userService.save(user);
		jaurnalRepo.deleteById(Id);
	}}catch(Exception e){
		System.out.println(e);
		throw new RuntimeException("AN Error occurred while deleteing the entry.", e);
	}
		return removed; 
		
	}
}
