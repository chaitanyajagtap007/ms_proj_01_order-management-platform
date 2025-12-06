package com.mh.crj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mh.crj.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
