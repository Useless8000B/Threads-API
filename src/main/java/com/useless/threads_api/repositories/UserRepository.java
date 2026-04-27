package com.useless.threads_api.repositories;

import com.useless.threads_api.model.UserModel;

public interface UserRepository {
	String saveUser(UserModel user) throws Exception;
}
