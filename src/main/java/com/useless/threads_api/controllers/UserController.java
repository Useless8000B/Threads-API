package com.useless.threads_api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.useless.threads_api.exceptions.ForbiddenException;
import com.useless.threads_api.model.UserModel;
import com.useless.threads_api.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserRepository _userRepository;

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody UserModel user, HttpServletRequest request) throws Exception {
		String uid = (String) request.getAttribute("uid");

		if (uid == null) {
			throw new ForbiddenException("User not authenticated!");
		}

		user.setUid(uid);
			String savedUser = _userRepository.saveUser(user);

			Map<String, String> response = new HashMap<>();

			response.put("uid", savedUser);
			response.put("status", "success");
			response.put("message", "User created!");

			return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
