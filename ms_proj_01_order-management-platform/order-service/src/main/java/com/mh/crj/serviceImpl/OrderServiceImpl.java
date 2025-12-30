package com.mh.crj.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mh.crj.client.ProductServiceClient;
import com.mh.crj.client.UserServiceClient;
import com.mh.crj.entity.OrderStatus;
import com.mh.crj.entity.Orders;
import com.mh.crj.exception.DuplicateOrderException;
import com.mh.crj.exception.InsufficientStockException;
import com.mh.crj.exception.OrderNotFoundException;
import com.mh.crj.exception.ProductNotFoundException;
import com.mh.crj.exception.UserNotFoundException;
import com.mh.crj.model.OrderRequestDto;
import com.mh.crj.model.ProductDto;
import com.mh.crj.model.ResponseMessage;
import com.mh.crj.model.UserDto;
import com.mh.crj.repository.OrderRepo;
import com.mh.crj.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private UserServiceClient userServiceClient;
	
	@Autowired
	private ProductServiceClient productServiceClient;
	
	@Override
	public Orders createOrder(OrderRequestDto orderRequestDto) {
		
		
		// 1️ Validate user exists
		UserDto user;
		try {
			user = userServiceClient.getUser(orderRequestDto.getUserId());
			
			if(user.getId()==null) {
				throw new UserNotFoundException("User not exists with id: " + orderRequestDto.getUserId());
			}
		}catch (Exception e) {
	        throw new UserNotFoundException("User not exists with id: " + orderRequestDto.getUserId());
	    }
		
		// 2 Validate product exists
		ProductDto product;
		try {
			product = productServiceClient.getProduct(orderRequestDto.getProductId());
			if(product.getId()==null) {
				throw new ProductNotFoundException("product is not exists with id: " + orderRequestDto.getUserId());
			}
		}catch (Exception e) {
	        throw new ProductNotFoundException("Product not exists with id: is " + orderRequestDto.getProductId());
	    }		
		
		// 3 Validate stock
		if(product.getStock() < orderRequestDto.getQuantity()) {
			throw new InsufficientStockException("Insufficient stock for product: " + product.getId());
	    }

	    // 4️ Prevent duplicate active order
		Optional<Orders> checkOrders = orderRepo.findByUserIdAndProductIdAndStatus(orderRequestDto.getUserId(), orderRequestDto.getProductId(),OrderStatus.CREATED);
		
		if(checkOrders.isPresent()) {
			throw new DuplicateOrderException("Order already exists for user: " + orderRequestDto.getUserId() +
	                " and product: " + orderRequestDto.getProductId());
		}
		
		// 5 Create order
		Orders order = Orders.builder()
				.userId(orderRequestDto.getUserId())
                .productId(orderRequestDto.getProductId())
                .quantity(orderRequestDto.getQuantity())
                .totalAmount(orderRequestDto.getTotalAmount())
                .status(OrderStatus.CREATED)
                .build();
		
		Orders createdOrder = orderRepo.save(order);
		return createdOrder;
	}
	
	
	@Override
	public Orders getOrder(Integer id) {
		Orders order = orderRepo.findById(id).orElseThrow(()-> new OrderNotFoundException("Ordes is not available with id "+id));
		return order;
	}
	
	@Override
	public List<Orders> getAllOrders() {
	
		List<Orders> allOrders = orderRepo.findAll();
		return allOrders;
	}
	
	
	@Override
	public Orders updateStatus(Integer id, OrderStatus status) {
		Orders byId = orderRepo.findById(id).orElseThrow(()-> new OrderNotFoundException("Ordes is not available with id "+id));
		byId.setStatus(status);
		Orders updatedOrder = orderRepo.save(byId);
		return updatedOrder;
	}
	
	
	@Override
	public String cancleOrder(Integer id) {
	
		Orders order = orderRepo.findById(id).orElseThrow(()-> new OrderNotFoundException("Ordes is not available with id "+id));
		order.setStatus(OrderStatus.CANCELLED);
	    orderRepo.save(order);
	    return "Order cancelled for user " + order.getUserId()
	            + " and order id " + order.getId();
	}
	
}
