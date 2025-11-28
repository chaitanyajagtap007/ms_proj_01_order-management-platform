package com.mh.crj.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mh.crj.entity.User;
import com.mh.crj.model.ResponseMessage;
import com.mh.crj.model.UserLoginDto;
import com.mh.crj.model.UserRegisterDto;
import com.mh.crj.service.UserService;
import com.mh.crj.utility.Constants;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/home")
	public String getMessage() {
		return "Hi let's Start Course Project";
	}
	
	@PostMapping("/add")
	public ResponseEntity<ResponseMessage> addUser(@RequestBody @Valid UserRegisterDto registerDto) {

		User savedtUser = userService.insertUser(registerDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"User save successfully",savedtUser));
	
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseMessage> getAllUser() {

		List<User> allUser = userService.fetchAllUser();
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"User fetch successfully",allUser));
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseMessage> loginUser(@RequestBody UserLoginDto loginDto) {

		User checkLogin = userService.checkLogin(loginDto);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"User fetch successfully",checkLogin));
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<ResponseMessage> loginUser(@PathVariable Integer id) {
		User userById = userService.getUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"User fetch successfully",userById));
	}
}
