package com.admin;

import java.sql.Date;

public class AdminOrder {
	
	private int orderId;
	private int customerId;
	private int productId;
	private int orderQty;
	private Date orderDate;
	
	
	public AdminOrder() {
		super();
	}


	public AdminOrder(int orderId, int customerId, int productId, int orderQty, Date orderDate) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.productId = productId;
		this.orderQty = orderQty;
		this.orderDate = orderDate;
	}


	public int getOrderId() {
		return orderId;
	}


	public int getCustomerId() {
		return customerId;
	}


	public int getProductId() {
		return productId;
	}


	public int getOrderQty() {
		return orderQty;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
	

}
