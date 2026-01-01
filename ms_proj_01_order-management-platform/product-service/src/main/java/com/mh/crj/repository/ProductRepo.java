package com.mh.crj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mh.crj.entity.Product;
import java.util.List;


public interface ProductRepo extends JpaRepository<Product, Integer> {

	Optional<Product> findByName(String name);
	
}
