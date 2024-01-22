package com.admin;

public class Admin {
	
	private int adminId;
	private String email;
	private String password;
	
	
	public Admin() {
		super();
	}


	public Admin(int adminId, String email, String password) {
		super();
		this.adminId = adminId;
		this.email = email;
		this.password = password;
	}


	public int getAdminId() {
		return adminId;
	}


	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return password;
	}


	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
