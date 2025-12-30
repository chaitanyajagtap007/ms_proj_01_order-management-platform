package com.mh.crj.serviceImpl;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.crj.entity.User;
import com.mh.crj.exception.DuplicateEmailException;
import com.mh.crj.exception.InternalServerException;
import com.mh.crj.exception.InvalidEmailPasswordException;
import com.mh.crj.exception.UserNotFoundException;
import com.mh.crj.model.UserDto;
import com.mh.crj.model.UserLoginDto;
import com.mh.crj.model.UserRegisterDto;
import com.mh.crj.repository.UserRepo;
import com.mh.crj.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User insertUser(UserRegisterDto registerDto) {

		User user = new User();
//		try {
			
			user.setFirstName(registerDto.getFirstName());
			user.setLastName(registerDto.getLastName());
			user.setEmail(registerDto.getEmail());
			user.setPassword(Base64.getEncoder().encodeToString(registerDto.getPassword().getBytes()));
			user.setMobile(registerDto.getMobile());
			try {
				User save = userRepo.save(user);
				return save;
			}catch (Exception e) {
				throw new DuplicateEmailException("email already exists! "+e.getMessage());
			}
//		}catch (InternalServerException e) {
//			throw new InternalServerException("Failed to insert User "+e.getMessage());
//		}
	}
	
	
	@Override
	public User updateUser(UserRegisterDto registerDto) {
		User user = userRepo.findByEmail(registerDto.getEmail()).orElseThrow(()-> new UserNotFoundException("User is not found in DB with email :"+registerDto.getEmail()));
		
		user.setFirstName(registerDto.getFirstName());
		user.setLastName(registerDto.getLastName());
		user.setMobile(registerDto.getMobile());
		User save = userRepo.save(user);
		return save;
	}
	
	
	@Override
	public List<User> fetchAllUser() {
		List<User> all = userRepo.findAll();
		
		if(all.isEmpty()) {
			throw new UserNotFoundException("Users is not available");
		}
		
		return all;
	}

	@Override
	public User checkLogin(UserLoginDto loginDto) {
			
		User byEmail = userRepo.findByEmail(loginDto.getEmail()).orElseThrow(()-> new UserNotFoundException("Invalid Email and User"));
		System.out.println(byEmail);

		if(!byEmail.getPassword().equals(Base64.getEncoder().encodeToString(loginDto.getPassword().getBytes()))) {
			throw new InvalidEmailPasswordException("Invalid Password ");
		}
		byEmail.setPassword(new String(Base64.getDecoder().decode(byEmail.getPassword())));
		
		return byEmail; 
	}
	
	
	@Override
	public User getUserById(Integer id) {
		User byId = userRepo.findById(id).orElseThrow(()->new UserNotFoundException("User is not availabe with id :"+id));
		return byId;
	}
	
	
	@Override
	public void deleteUser(Integer id) {
		User user = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("User is not abailable in DB with id :"+id));
		userRepo.delete(user);
	}
	
}
