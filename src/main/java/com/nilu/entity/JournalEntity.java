package com.nilu.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Document(collection = "JournalEntity")
@Data
public class JournalEntity {

	@Id
	private ObjectId id;
	@NonNull
	private String title;

	private String Content;

	private LocalDateTime date;



}
