package com.useless.threads_api.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.useless.threads_api.models.PostModel;
import com.google.cloud.firestore.Query;

@Repository
public class PostRepository {
	private final Firestore _firestore;

	public PostRepository(Firestore firestore) {
		this._firestore = firestore;
	}

	public String savePost(PostModel post) throws Exception {
		ApiFuture<DocumentReference> future = _firestore.collection("posts").add(post);
		return future.get().getId();
	}

	public List<PostModel> getAllPosts() throws Exception {
		ApiFuture<QuerySnapshot> query = _firestore
				.collection("posts")
				.orderBy("createdAt", Query.Direction.DESCENDING).get();

		return query.get().toObjects(PostModel.class);

	}

	public List<PostModel> findByUuid(String uid) throws Exception {
		ApiFuture<QuerySnapshot> query = _firestore
		.collection("posts")
		.whereEqualTo("uid", uid)
		.orderBy("createdAt", Query.Direction.DESCENDING)
		.get();

		return query.get().toObjects(PostModel.class);
	}
}
