package com.useless.threads_api.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.ServerTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostModel {
	@DocumentId
	@JsonProperty("id")
	private String id;

	private String uid;
	private String username;
	private String text;
	private String profilePic;

	@ServerTimestamp
	private Date createdAt;
}
