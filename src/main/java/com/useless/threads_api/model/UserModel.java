package com.useless.threads_api.model;

import com.google.cloud.Date;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.ServerTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
	@DocumentId
	private String id;
	private String bio;

	@ServerTimestamp
	Date createdAt;

	String email;
	String fullName;
	String photoUrl;
	String uid;
	String username;
}
