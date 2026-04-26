package com.useless.threads_api.exceptions;

public class NotFoundException extends RuntimeException {
	NotFoundException(String message) {
		super(message);
	}
}