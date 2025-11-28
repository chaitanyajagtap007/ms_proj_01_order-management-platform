package com.mh.crj.service;

import java.util.List;

import com.mh.crj.entity.User;
import com.mh.crj.model.UserLoginDto;
import com.mh.crj.model.UserRegisterDto;

public interface UserService {

	public User insertUser(UserRegisterDto registerDto);

	public List<User> fetchAllUser();

	public User checkLogin(UserLoginDto loginDto);

	public User getUserById(Integer id);

	public User updateUser(UserRegisterDto registerDto);

}
