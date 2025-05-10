package com.nilu.entity;


import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	private ObjectId id;
	@NonNull
	@Indexed(unique = true)
	private String name;

	@NonNull
	private String email;
	@NonNull
	private String password;

	// create referance of journalEntriy this is work as forgien key like
	@DBRef
    private List<JournalEntity> journalEntity = new ArrayList<>();

     private List<String> roles;


}
