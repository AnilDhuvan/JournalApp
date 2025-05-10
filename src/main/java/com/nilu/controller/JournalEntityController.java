package com.nilu.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.nilu.entity.JournalEntity;


//@Controller
//@RequestMapping("/journal")
public class JournalEntityController {


	private Map<Integer, JournalEntity> journalEntity = new HashMap<>();



//	public JournalEntityController() {
//
//		this.journalEntity.put(1, new JournalEntity("101", "Anil Kumar", "My Fast App"));
//		this.journalEntity.put(2, new JournalEntity("102", "Lokesh Kumar", "My seven App"));
//		this.journalEntity.put(3, new JournalEntity("103", "Aman Kumar", "My Ten App"));
//	}



//	@GetMapping("/get-data")
	public ResponseEntity<Map<Integer, JournalEntity>> getJournalData() {

		if (journalEntity != null && !journalEntity.isEmpty()) {
			return new ResponseEntity<>(journalEntity, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(Collections.emptyMap(), HttpStatus.NO_CONTENT);
		}
	}


//	@GetMapping("/get-data/{id}")
	public ResponseEntity<JournalEntity> getJournalDataByID(@PathVariable int id) {
		JournalEntity journdata = journalEntity.get(id);

		if (journdata != null) {
			return new ResponseEntity<>(journdata, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}


//	@PostMapping
//	public ResponseEntity<JournalEntity> createRecord(@RequestBody JournalEntity myEntity){
//		 JournalEntity.put(myEntity.getId(), myEntity);
//		return new ResponseEntity<>(put,HttpStatus.CREATED);
//	}




}
