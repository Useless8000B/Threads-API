package com.useless.threads_api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useless.threads_api.exceptions.UnauthorizedException;
import com.useless.threads_api.model.PostModel;
import com.useless.threads_api.repository.PostRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
	private final PostRepository _postRepository;

	@PostMapping
	public ResponseEntity<String> createPost(@RequestBody PostModel post, HttpServletRequest request) throws Exception {
		String uuid = (String) request.getAttribute("uuid");

		if (uuid == null) {
			throw new UnauthorizedException("User not authenticated!");
		}

		post.setUid(uuid);

		String id = _postRepository.savePost(post);

		return ResponseEntity.status(201).body(id);
	}

	@GetMapping
	public ResponseEntity<List<PostModel>> listPosts(HttpServletRequest request) throws Exception {
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
