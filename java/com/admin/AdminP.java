package com.admin;

public class AdminP {
	
	private int id;
	private String  name;
	private String size;
	private Double price;
	private String image;
	private int categoryId;
	
	
	public AdminP() {
		super();
	}


	public AdminP(int id, String name, String size, Double price, String image, int categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.price = price;
		this.image = image;
		this.categoryId = categoryId;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getSize() {
		return size;
	}


	public Double getPrice() {
		return price;
	}


	public String getImage() {
		return image;
	}
	
	public int getCategoryId() {
		return categoryId;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public void setImage(String image) {
		this.image = image;
	}
	


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	

}
