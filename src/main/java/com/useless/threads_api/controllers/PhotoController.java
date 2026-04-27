package com.useless.threads_api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.cloud.firestore.Firestore;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PhotoController {
	private final Cloudinary _cloudinary;
	private final Firestore _firestore;

	@PostMapping("/upload-photo")
	public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws Exception {
		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("Empty file");
		}

		String contentType = file.getContentType();
		String fileName = file.getOriginalFilename();

		boolean isImage = (contentType != null && contentType.startsWith("image/")) ||
				(fileName != null && fileName.toLowerCase().matches(".*\\.(jpg|jpeg|png|webp|gif)$"));

		if (!isImage) {
			return ResponseEntity.badRequest().body("Only images are allowed!");
		}

		String uid = (String) request.getAttribute("uid");

		@SuppressWarnings("unchecked")
		Map<String, Object> uploadResult = _cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
		String imageUrl = (String) uploadResult.get("secure_url");

		Map<String, Object> update = new HashMap<>();
		update.put("photoUrl", imageUrl);
		_firestore.collection("users").document(uid).update(update);

		return ResponseEntity.ok(Map.of("url", imageUrl));
	}
}
