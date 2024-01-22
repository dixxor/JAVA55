package com.product;

public class OrderDetail {
	private double subTotal, total, shipping, tax;
	
	public OrderDetail(String subTotal, String total, String shipping, String tax) {
		this.subTotal = Double.parseDouble(subTotal);
		this.total = Double.parseDouble(total);
		this.shipping = Double.parseDouble(shipping);
		this.tax = Double.parseDouble(tax);
	}
	
	public String getSubTotal() {
		return String.format("%.2f", subTotal);
	}
	
	public String getTotal() {
		return String.format("%.2f", total);
	}

	public String getShipping() {
		return String.format("%.2f", shipping);
	}

	public String getTax() {
		return String.format("%.2f", tax);
	}


}
