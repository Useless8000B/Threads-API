package com.useless.threads_api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useless.threads_api.exceptions.ForbiddenException;
import com.useless.threads_api.exceptions.NotFoundException;
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
		String uid = (String) request.getAttribute("uid");

		if (uid == null) {
			throw new UnauthorizedException("User not authenticated!");
		}

		post.setUid(uid);

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

		List<PostModel> posts = _postRepository.findByUid(uid);

		return ResponseEntity.ok(posts);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable String id, HttpServletRequest request) throws Exception {
		String uid = (String) request.getAttribute("uid");

		if (uid == null) {
			throw new UnauthorizedException("User not authenticated");
		}

		PostModel post = _postRepository.findbyId(id);

		if (post == null) {
			throw new NotFoundException("Post not found!");
		}

		if (!post.getUid().equals(uid)) {
			throw new ForbiddenException("You don't have permisson to delete this post");
		}

		_postRepository.removePost(id);
		return ResponseEntity.ok("Post removed");
	}
}
