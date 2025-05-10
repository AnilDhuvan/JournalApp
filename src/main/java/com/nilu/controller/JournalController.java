package com.nilu.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nilu.entity.JournalEntity;
import com.nilu.entity.User;
import com.nilu.service.JournalService;
import com.nilu.service.JournalServiceImpl;
import com.nilu.service.UserServiceImpl;

@RestController
@RequestMapping("/journal")
public class JournalController {

	@Autowired
	private JournalServiceImpl  journalService;
	
	@Autowired
	private UserServiceImpl userService;

	@GetMapping()
	public ResponseEntity<?> getUserJournal() {
		// to spring Security
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				String name = authentication.getName();
				
		 User exitsUserNameORNot = userService.findByName(name);
		if (exitsUserNameORNot == null) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
		List<JournalEntity> journalEntity = exitsUserNameORNot.getJournalEntity();
	//	return new ResponseEntity<>(all, HttpStatus.OK);
		return ResponseEntity.ok(journalEntity);
	}


	@PostMapping()
	public ResponseEntity<?> createEntity(@RequestBody JournalEntity journalEntity) {
		
		// to spring Security
					Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
					String name = authentication.getName();
		try {
			
			User user = userService.findByName(name);
			if (user != null) {
				journalService.save(journalEntity,user);
				//	exitsUserNameORNot.setJournalEntity(save.getId());
					return new ResponseEntity<>(journalEntity, HttpStatus.CREATED);
				}
       	return new ResponseEntity<> ("This name user not exits" ,HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}



	@GetMapping("id/{myId}")
	public ResponseEntity<?> getJournalEntityById(@PathVariable ObjectId myId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		User user = userService.findByName(name);
		List<JournalEntity> collect = user.getJournalEntity().stream().filter(x -> x.getId().equals(myId))
				.collect(Collectors.toList());

		if (!collect.isEmpty()) {
			Optional<JournalEntity> byId = journalService.findById(myId);
			if (byId.isPresent()) {
				return new ResponseEntity<>(byId.get(), HttpStatus.OK);
			}

		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		

		
	}


	
	@DeleteMapping("id/{myId}")
	public ResponseEntity<?> deleteJournalEntityById(@PathVariable ObjectId myId) {
		// to spring Security
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		boolean removed = journalService.deleteById(myId,userName);
//		userService.findByName(userName).getJournalEntity().remove(myId);
		if(removed) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@PutMapping("id/{id}")
	public ResponseEntity<?> updateById(@PathVariable ObjectId id, @RequestBody JournalEntity newEntity) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		User user = userService.findByName(userName);
		List<JournalEntity> collect = user.getJournalEntity().stream().filter(x -> x.getId().equals(id))
				.collect(Collectors.toList());

		if (!collect.isEmpty()) {
			Optional<JournalEntity> jurnalEntity = journalService.findById(id);
		
		if (jurnalEntity.isPresent()) {
			
		JournalEntity old = jurnalEntity.get();
			old.setTitle(newEntity.getTitle() != null && !newEntity.getTitle().equals("") ? newEntity.getTitle()
					: old.getTitle());
			old.setContent(newEntity.getContent() != null && !newEntity.getContent().equals("") ? newEntity.getContent()
					: old.getContent());
			journalService.save(old);
			return new ResponseEntity<>(old, HttpStatus.OK);
		}}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	}
