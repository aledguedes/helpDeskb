package com.aledguedes.helpdesk.helpdesk.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aledguedes.helpdesk.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.aledguedes.helpdesk.helpdesk.services.exceptions.ObjectnotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectnotFoundException.class)
	public ResponseEntity<StandardError> objectnotFoundException(ObjectnotFoundException ex, 
			HttpServletRequest request){
		StandardError error = new StandardError(System.currentTimeMillis(), 
				HttpStatus.NOT_FOUND.value(), "Object not Found",
				ex.getMessage(), request.getRequestURI());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, 
			HttpServletRequest request){
		StandardError error = new StandardError(System.currentTimeMillis(), 
				HttpStatus.BAD_REQUEST.value(), "Violation of data",
				ex.getMessage(), request.getRequestURI());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationError(MethodArgumentNotValidException ex, 
			HttpServletRequest request){
		ValidationError errors = new ValidationError(System.currentTimeMillis(), 
				HttpStatus.BAD_REQUEST.value(), "Validation error",
				"Field validation error", request.getRequestURI());
		for(FieldError x : ex.getBindingResult().getFieldErrors()) {
			errors.addError(x.getField(), x.getDefaultMessage());
		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		
	}

}
