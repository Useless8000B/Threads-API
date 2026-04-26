package com.useless.threads_api.dto;

import java.time.LocalDateTime;

public record ErrorResponse (     
	LocalDateTime timestamp,
	int status,
	String error,
	String message,
	String path
) {}
