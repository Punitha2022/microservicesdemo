package com.sample.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sample.orderservice.entities.Order;
import com.sample.orderservice.entities.Payment;
import com.sample.orderservice.services.OrderService;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
	private OrderService orderService;
   
	@RequestMapping("/order/createorder")
	public ResponseEntity<?> createOrder(@RequestBody Order order){
	 Order savedOrder=	orderService.createOrder(order);
	 //If payment is successful , orderStatus = Paid, else orderStatus=Pending
	 
	 return new ResponseEntity<Order>(savedOrder,HttpStatus.OK);
	}
	@GetMapping("/{orderid}")
	public ResponseEntity<?> getOrderDetails(@PathVariable int orderid){
		Order order=orderService.getOrder(orderid);
		if(order==null)
			return new ResponseEntity<String>("Order Not found",HttpStatus.OK);
		else
			return new ResponseEntity<Order>(order,HttpStatus.OK);
		
	}
}







