package com.sample.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.orderservice.entities.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
