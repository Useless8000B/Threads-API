package com.useless.threads_api.repository;

import com.useless.threads_api.model.PostModel;
import java.util.List;

public interface PostRepository {
    String savePost(PostModel post) throws Exception;
    List<PostModel> getAllPosts() throws Exception;
    List<PostModel> findByUid(String uid) throws Exception;
    PostModel findbyId(String id) throws Exception;
    void removePost(String id) throws Exception;
}
