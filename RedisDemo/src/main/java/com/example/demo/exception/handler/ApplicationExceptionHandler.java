package com.example.demo.exception.handler;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exception.ApplicationException;
import com.example.demo.models.AppErrorResponse;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = ApplicationException.class)
	public ResponseEntity<AppErrorResponse> buildErrorResponse(Exception exception,
			HttpServletRequest httpServletRequest) {
		AppErrorResponse appErrorResponse = AppErrorResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.message(exception.getMessage()).path(httpServletRequest.getRequestURI())
				.localDateTime(LocalDateTime.now()).build();
		return new ResponseEntity<AppErrorResponse>(appErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
