/**
 * 
 */
package com.easytobuy;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Satish Reddy
 *
 */
@ControllerAdvice
public class ProductAPIExceptionHandler {
    @ExceptionHandler(value = {ProductAPIException.class})
	public ResponseEntity<Object> productAPIExceptionHandler(ProductAPIException apiException){
		HttpStatus httpStatus=HttpStatus.BAD_REQUEST;
		new ProductException(apiException.getMessage(), apiException, httpStatus, ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<>(apiException,httpStatus);
		
	}
}
