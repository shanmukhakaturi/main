package com.ros.inventory.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorHandler {
	
	private final String status = "error"; 
	
	private int code;
	
	private String message ;
	

}