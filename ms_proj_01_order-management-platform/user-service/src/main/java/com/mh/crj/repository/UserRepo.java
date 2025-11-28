package com.mh.crj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mh.crj.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	public Optional<User> findByEmail(String email);

}
