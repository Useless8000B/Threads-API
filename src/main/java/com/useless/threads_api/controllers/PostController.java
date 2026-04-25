package com.useless.threads_api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useless.threads_api.models.PostModel;
import com.useless.threads_api.repositories.PostRepository;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	private final PostRepository _postRepository;

	public PostController(PostRepository postRepository) {
		this._postRepository = postRepository;
	}

	@PostMapping
	public ResponseEntity<String> createPost(@RequestBody PostModel post) {
		try {
			String id = _postRepository.savePost(post);
			return ResponseEntity.status(201).body(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("Error creating user");
		}
	}

	@GetMapping
	public ResponseEntity<List<PostModel>> listPosts() {
		try {
			List<PostModel> posts = _postRepository.getAllPosts();
			return ResponseEntity.ok(posts);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
