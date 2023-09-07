package com.sample.orderservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sample.orderservice.entities.Order;
import com.sample.orderservice.entities.Payment;
import com.sample.orderservice.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private RestTemplate restTemplate;

	@CircuitBreaker(name="CircuitBreakerService",
			fallbackMethod = "orderserviceFallBackMethod")
	public Order createOrder(Order order) {
		Order savedOrder = orderRepo.save(order);//Insert query
		Payment payment=new Payment();
		payment.setOrderid(savedOrder.getOrderid());
		payment.setAmount(savedOrder.getAmount());
		//calling PAYMENT-SERVICE (microservice) from ORDER-SERVICE
		//SERVICE-REGISTRY (Eureka Server)
		Payment paymentResponse=
		restTemplate.postForObject("http://API-GATEWAY/payments/payamount", payment, Payment.class);
		if(paymentResponse.getPaymentStatus().equals("Success"))
		{
			savedOrder.setOrderStatus("Paid");
		}
		else
			savedOrder.setOrderStatus("Pending");
		return orderRepo.save(savedOrder);//update the orderStatus.
	}

	public Order getOrder(int orderid) {
		Optional< Order> order=orderRepo.findById(orderid);
		if(order.isPresent())
				return order.get();
		else
		return null;
	}

	public Order orderserviceFallBackMethod(Throwable throwable) {
		Order order=new Order();
		order.setOrderStatus("Payment Service is currently down.. please try after some time");
		return order; 
	}

}
