package com.useless.threads_api.exceptions;

public class NotFoundExceptionHandler extends RuntimeException {
	NotFoundExceptionHandler() {
		super("Not found!");
	}
}