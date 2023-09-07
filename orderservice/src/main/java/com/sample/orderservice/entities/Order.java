package com.sample.orderservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name="order_details")
@NoArgsConstructor //no arg constructor - default constructor
@AllArgsConstructor  // parameterized constructor
@Data //getters and setters,equals ,hashCode,toString
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int orderid;
private int productid;
private int quantity;
private double amount;
private String orderStatus;

}






