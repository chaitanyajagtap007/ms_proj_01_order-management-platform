package com.mh.crj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mh.crj.entity.Orders;

public interface OrderRepo extends JpaRepository<Orders, Integer> {

}
