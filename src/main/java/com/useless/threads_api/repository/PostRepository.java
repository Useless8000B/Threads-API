package com.useless.threads_api.repository;

import java.util.List;

import com.useless.threads_api.model.PostModel;

public interface PostRepository {
	String savePost(PostModel post) throws Exception;
	List<PostModel> getAllPosts() throws Exception;
	List<PostModel> findByUuid(String uid) throws Exception;
}
