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

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	private final PostRepository _postRepository;

	public PostController(PostRepository postRepository) {
		this._postRepository = postRepository;
	}

	@PostMapping
	public ResponseEntity<String> createPost(@RequestBody PostModel post) throws Exception {
		String id = _postRepository.savePost(post);
		return ResponseEntity.status(201).body(id);
	}

	@GetMapping
	public ResponseEntity<List<PostModel>> listPosts() throws Exception {
		List<PostModel> posts = _postRepository.getAllPosts();
		return ResponseEntity.ok(posts);
	}

	@GetMapping("/me")
	public ResponseEntity<List<PostModel>> getMyPosts(HttpServletRequest request) throws Exception {
		String uid = (String) request.getAttribute("uid");

		List<PostModel> posts = _postRepository.findByUuid(uid);

		return ResponseEntity.ok(posts);
	}
}
