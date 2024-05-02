package com.UIBS.Enquiry_Module.CustomeExceptionHandler;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.UIBS.Enquiry_Module.model.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestExceptioinHandler {
	
	@ExceptionHandler(EnquiryNotSubmitException.class)
	public ResponseEntity<ApiResponse> handleCustomeExceptionStatus(EnquiryNotSubmitException e, HttpServletRequest request){
		
		log.info("handleCustomeExceptionStatus ApiResponse Executed");
		ApiResponse error = new ApiResponse();
		error.setDate(new Date());
		error.setMessage(e.getMessage());
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
		error.setHttpStatusMessage(HttpStatus.NOT_FOUND);
		error.setUriPath(request.getRequestURI());
		
		log.info("all APIResponse set");
		return new ResponseEntity<ApiResponse>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
		
		log.info("handleMethodArgsNotValidException executed");
		Map<String, String> responce = new HashMap<>();
		
		List<ObjectError> list = ex.getBindingResult().getAllErrors();
		Map<ApiResponse,Map<String, String>> allresp = new HashMap<>();
		
		for(ObjectError oe : list) {
			FieldError f = (FieldError) oe;
			responce.put(f.getField(), f.getDefaultMessage());

		}
		log.info("handleMethodArgsNotValidException Response generated with fields and default message");
						
		return new ResponseEntity<Map<String, String>>(responce,HttpStatus.BAD_REQUEST);
		
	}
}
