package com.mh.crj.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mh.crj.model.ResponseMessage;

@FeignClient(name ="user-service", url = "http://localhost:9090/user")
public interface UserServiceClient {
	@GetMapping("/getById/{id}")
	public ResponseMessage getUser(@PathVariable Integer id);
}




//package com.mh.crj.client;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.mh.crj.entity.Booking;
//import com.mh.crj.model.ResponseMessage;
//
//@FeignClient(name = "BOOKINGSERVICE", url = "http://localhost:8081/booking")
//public interface BookingServiceClient {
//
//
//	@PostMapping("/create")
//	public ResponseMessage createBooking(Booking booking);
//	
//
//	@PostMapping("/cancel/{bookingId}")
//	public ResponseMessage cancelBooking(@PathVariable Long bookingId);
//}
