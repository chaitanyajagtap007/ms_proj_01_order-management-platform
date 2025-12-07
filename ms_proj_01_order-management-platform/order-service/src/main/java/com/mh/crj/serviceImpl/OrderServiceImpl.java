package com.mh.crj.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.crj.client.UserServiceClient;
import com.mh.crj.entity.OrderStatus;
import com.mh.crj.entity.Orders;
import com.mh.crj.exception.DuplicateOrderException;
import com.mh.crj.exception.OrderNotFoundException;
import com.mh.crj.exception.UserNotFoundException;
import com.mh.crj.model.OrderRequestDto;
import com.mh.crj.model.ResponseMessage;
import com.mh.crj.repository.OrderRepo;
import com.mh.crj.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private UserServiceClient userServiceClient;
	
	@Override
	public Orders createOrder(OrderRequestDto orderRequestDto) {

		ResponseMessage userResponse = userServiceClient.getUser(orderRequestDto.getUserId());
		
		if(userResponse.getData()==null) {
			throw new UserNotFoundException("User is not exists with id: "+orderRequestDto.getUserId());
		}
		
		System.err.println(userResponse);
		System.out.println(userResponse.getData());
		
		Optional<Orders> checkOrders = orderRepo.findByUserIdAndProductIdAndStatus(orderRequestDto.getUserId(), orderRequestDto.getProductId(),OrderStatus.CREATED);
		
		
		if(checkOrders.isPresent()) {
		
			throw new DuplicateOrderException(
	                "Order already exists for user: " + orderRequestDto.getUserId() +
	                " and product: " + orderRequestDto.getProductId()
	        );
		}
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
		orderRepo.delete(order);
		return "Order is cancled for User :"+order.getUserId()+" and the order id is :"+order.getId();

	}
	
}
