package com.sample.orderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
private int paymentid;
private int orderid;
private double amount;
private String paymentStatus;
public Payment(int orderid,double amount) {
	this.orderid=orderid;
	this.amount=amount;
}

}
