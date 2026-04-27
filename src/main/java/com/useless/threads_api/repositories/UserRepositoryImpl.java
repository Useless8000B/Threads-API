package com.useless.threads_api.repositories;

import org.springframework.stereotype.Repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.useless.threads_api.exceptions.ForbiddenException;
import com.useless.threads_api.model.UserModel;

@Repository
public class UserRepositoryImpl implements UserRepository {
	private Firestore _firestore;

	public UserRepositoryImpl(Firestore firestore) {
		this._firestore = firestore;
	}

	public String saveUser(UserModel user) throws Exception {
		ApiFuture<QuerySnapshot> query = _firestore
		.collection("users")
		.whereEqualTo("username", user.getUsername())
		.get();

		if (!query.get().isEmpty()) {
			throw new ForbiddenException("User already exists!");
		}

		_firestore.collection("users").document(user.getId()).set(user);

		return user.getUid();
	}
}
