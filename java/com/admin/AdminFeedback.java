package com.admin;

public class AdminFeedback {
	
	private int userId;
	private String name;
	private String email;
	private String feedback;
	
	
	public AdminFeedback() {
		super();
	}


	public AdminFeedback(int userId, String name, String email, String feedback) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.feedback = feedback;
	}


	public int getUserId() {
		return userId;
	}


	public String getName() {
		return name;
	}


	public String getEmail() {
		return email;
	}


	public String getFeedback() {
		return feedback;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	

}
