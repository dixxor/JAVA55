package com.admin;

public class AdminDelivery {
	
	private int orderId;
	private int customerId;
	private String name;
	private String phone;
	private String addres;
	private String total;
	
	
	public AdminDelivery() {
		super();
	}


	public AdminDelivery(int orderId, int customerId, String name, String phone, String addres, String total) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.name = name;
		this.phone = phone;
		this.addres = addres;
		this.total = total;
	}


	public int getOrderId() {
		return orderId;
	}


	public int getCustomerId() {
		return customerId;
	}


	public String getName() {
		return name;
	}


	public String getPhone() {
		return phone;
	}


	public String getAddres() {
		return addres;
	}


	public String getTotal() {
		return total;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public void setAddres(String addres) {
		this.addres = addres;
	}


	public void setTotal(String total) {
		this.total = total;
	}
	
	
	

}


