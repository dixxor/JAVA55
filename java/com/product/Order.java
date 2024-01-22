package com.product;

public class Order extends Product{
	
	private int customerId;
	private int orderQty;
	
	
	public Order() {
		
	}


	public Order(int customerId, int orderQty) {
		super();
		this.customerId = customerId;
		this.orderQty = orderQty;
	}


	public int getCustomerId() {
		return customerId;
	}


	public int getOrderQty() {
		return orderQty;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}


	@Override
	public String toString() {
		return "Order [customerId=" + customerId + ", orderQty=" + orderQty + "]";
	}
	
	

}
