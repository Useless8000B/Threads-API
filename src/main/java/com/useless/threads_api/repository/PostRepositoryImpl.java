package com.useless.threads_api.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.useless.threads_api.model.PostModel;
import com.google.cloud.firestore.Query;

@Repository
public class PostRepositoryImpl implements PostRepository {
	private final Firestore _firestore;

	public PostRepositoryImpl(Firestore firestore) {
		this._firestore = firestore;
	}

	@Override
	public String savePost(PostModel post) throws Exception {
		ApiFuture<DocumentReference> future = _firestore.collection("posts").add(post);
		return future.get().getId();
	}

	@Override
	public List<PostModel> getAllPosts() throws Exception {
		ApiFuture<QuerySnapshot> query = _firestore
				.collection("posts")
				.orderBy("createdAt", Query.Direction.DESCENDING)
				.limit(50)
				.get();

		return query.get().toObjects(PostModel.class);
	}

	@Override
	public List<PostModel> findByUid(String uid) throws Exception {
		ApiFuture<QuerySnapshot> query = _firestore
		.collection("posts")
		.whereEqualTo("uid", uid)
		.orderBy("createdAt", Query.Direction.DESCENDING)
		.get();

		return query.get().toObjects(PostModel.class);
	}

	@Override
	public PostModel findbyId(String id) throws Exception {
		DocumentReference doc = _firestore.collection("posts").document(id);

		ApiFuture<DocumentSnapshot> future = doc.get();

		DocumentSnapshot document = future.get();

		if (document.exists()) {
			return document.toObject(PostModel.class);
		} else {
			return null;
		}
	}

	@Override
	public void removePost(String id) throws Exception {
		DocumentReference doc = _firestore.collection("posts").document(id);

		ApiFuture<WriteResult> delete = doc.delete();

		delete.get();
	}
}
