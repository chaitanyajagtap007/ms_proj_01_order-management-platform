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
		
		System.err.println("In create order method with dto"+orderRequestDto);
		
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
		
		
		System.err.println("User validation is completed user is present user: "+user);
		
		// 2 Validate product exists
		ProductDto product;
		try {
			product = productServiceClient.getProduct(orderRequestDto.getProductId());
			if(product.getId()==null) {
				throw new ProductNotFoundException("product is not exists with id: " + orderRequestDto.getProductId());
			}
		}catch (Exception e) {
	        throw new ProductNotFoundException("Product not exists with id: is " + orderRequestDto.getProductId());
	    }		
		
		// 3 Validate stock
		if(product.getStock() < orderRequestDto.getQuantity()) {
			throw new InsufficientStockException("Insufficient stock for product: " + product.getId());
	    }


		System.err.println("Product validation is completed product is present user: "+product);
		
		// 4️ Prevent duplicate active order
		Optional<Orders> checkOrders = orderRepo.findByUserIdAndProductIdAndStatus(orderRequestDto.getUserId(), orderRequestDto.getProductId(),OrderStatus.CREATED);
		
		if(checkOrders.isPresent()) {
			throw new DuplicateOrderException("Order already exists for user: " + orderRequestDto.getUserId() +
	                " and product: " + orderRequestDto.getProductId());
		}
		
		System.err.println("Stock valadition is also completed stock is available "+product.getStock());

		Double totalAmt = (orderRequestDto.getQuantity() * product.getPrice());
		System.err.println("the quentity is "+orderRequestDto.getQuantity()+" and the price is "+product.getPrice()+" and the total price is :"+totalAmt);
		// 5 Create order
		Orders order = Orders.builder()
				.userId(orderRequestDto.getUserId())
                .productId(orderRequestDto.getProductId())
                .quantity(orderRequestDto.getQuantity())
                .totalAmount(totalAmt)
                .status(OrderStatus.CREATED)
                .build();
		
		System.err.println("creare Order object for save this object into db.."+order);
		
		Orders createdOrder = orderRepo.save(order);
		
		System.err.println("Order is created...! ");
		
		// reduce stock
//		productServiceClient.updateStock(product.getId(), (product.getStock()-orderRequestDto.getQuantity()));
//		
//		System.err.println("stock reduce operation is completed..!");
		
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
		if(allOrders.size()==0) {
			throw new OrderNotFoundException("orders is not availables..!");
		}
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
	    
	    
//	  	//	Restore stock
//	    ProductDto product = productServiceClient.getProduct(order.getProductId());
//	    productServiceClient.updateStock(order.getProductId(), (product.getStock() + order.getQuantity()));
	    return "Order cancelled for user " + order.getUserId()
	            + " and order id " + order.getId();
	}
	
}
