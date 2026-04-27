package com.useless.threads_api.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.useless.threads_api.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return buildErrorResponse(status, e, request);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		return buildErrorResponse(status, e, request);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		return buildErrorResponse(status, e, request);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		return buildErrorResponse(status, e, request);
	}

	private ResponseEntity<ErrorResponse> buildErrorResponse(
			HttpStatus status,
			Exception e,
			HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(
				LocalDateTime.now(),
				status.value(),
				status.getReasonPhrase(),
				e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status).body(error);
	}
}
