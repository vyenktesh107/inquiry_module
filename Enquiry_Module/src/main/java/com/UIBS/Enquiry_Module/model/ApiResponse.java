package com.UIBS.Enquiry_Module.model;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

	private int statusCode;
	private String message;
	private HttpStatus httpStatusMessage;
	private String uriPath;
	private Date date;
}
