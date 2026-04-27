package com.useless.threads_api.model;

import java.time.LocalDateTime;

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
	LocalDateTime createdAt;
	String email;
	String fullName;
	String photoUrl;
	String uid;
	String username;
}
