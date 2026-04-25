package com.useless.threads_api.models;

import java.util.Date;

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
	private String id;

	private String uid;
	private String username;
	private String text;

	@ServerTimestamp
	private Date createdAt;
}
