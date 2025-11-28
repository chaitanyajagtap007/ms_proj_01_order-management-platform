package com.mh.crj.exception;

import java.net.HttpURLConnection;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mh.crj.model.ErrorResponseMessage;
import com.mh.crj.utility.Constants;

@RestControllerAdvice
public class GlobleExceptionHandler {

	
	@ExceptionHandler(InternalServerException.class)
	public ResponseEntity<Object> handleInternalServerException(InternalServerException ex){
		HashMap<String, String> hm = new HashMap<>();
		hm.put("Error Details", "Internal Server Error");
		hm.put("Error Messae", ex.getLocalizedMessage());
		hm.put("TimeStamp",System.currentTimeMillis()+"");
		
		ErrorResponseMessage errorResponseMessage = new ErrorResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE,"Internal server Error",hm);
		
		return ResponseEntity.ok(errorResponseMessage);
	}
	
	@ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Object> handleDuplicateKey(DuplicateEmailException ex) {
		HashMap<String, String> hm = new HashMap<>();
		hm.put("Error Details", "Email already exists!");
		hm.put("Error Messae", ex.getLocalizedMessage());
		hm.put("TimeStamp",System.currentTimeMillis()+"");
		
		ErrorResponseMessage errorResponseMessage = new ErrorResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE,"email is already present in DB",hm);
		
		return ResponseEntity.ok(errorResponseMessage);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleDuplicateKey(UserNotFoundException ex) {
		HashMap<String, String> hm = new HashMap<>();
		hm.put("Error Details", "User not exists! or incorrect email");
		hm.put("Error Messae", ex.getLocalizedMessage());
		hm.put("TimeStamp",System.currentTimeMillis()+"");
		
		ErrorResponseMessage errorResponseMessage = new ErrorResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE,"User is not available in DB",hm);
		
		return ResponseEntity.ok(errorResponseMessage);
	}
	
	@ExceptionHandler(InvalidEmailPasswordException.class)
	public ResponseEntity<Object> handleDuplicateKey(InvalidEmailPasswordException ex) {
		HashMap<String, String> hm = new HashMap<>();
		hm.put("Error Details", "Incorrect password");
		hm.put("Error Messae", ex.getLocalizedMessage());
		hm.put("TimeStamp",System.currentTimeMillis()+"");
		
		ErrorResponseMessage errorResponseMessage = new ErrorResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE,"Password is wrong",hm);
		
		return ResponseEntity.ok(errorResponseMessage);
	}
	
	
}
