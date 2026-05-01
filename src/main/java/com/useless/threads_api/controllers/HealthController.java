package com.useless.threads_api.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthController {
	@GetMapping
	public ResponseEntity<Map<String, String>> keepAlive() {
		Map<String, String> response = new HashMap<>();
		response.put("status", "up");
		response.put("timestamp", LocalDateTime.now().toString());

		return ResponseEntity.ok(response);
	}
}
