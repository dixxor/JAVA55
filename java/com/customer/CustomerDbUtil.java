package com.customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDbUtil {
	private static boolean isSuccess;
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	public static boolean validateLogin(String email, String password) {
		try {
			con = DbConnect.getConnection();
			stmt = con.createStatement();
			
			String sql = "select * from customer where email = '"+email+"' and password = '"+password+"' ";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				isSuccess = true;
			}else {
				isSuccess = false;
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	public static Customer userLogin(String email, String password) {
		Customer customer = null;
		try {
			con = DbConnect.getConnection();
			stmt = con.createStatement();
			
			String sql = "select * from supermarket.customer where email = '"+email+"' and password = '"+password+"' ";
			rs = stmt.executeQuery(sql);
			
			//next is a boolean. returns true if the sql query is right
			if(rs.next()) {
				customer = new Customer(); //creating a new instance
				customer.setId(rs.getInt(1)); //customer.setId(rs.getInt("customer_id");
				customer.setName(rs.getString(2));
				customer.setEmail(rs.getString(3));
				customer.setPhone(rs.getString(4));
				customer.setUsername(rs.getString(5));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return customer;
	}
	
	//returns a customer array list
	public static List<Customer> getCustomer(String email, String password){
	
		ArrayList<Customer> cus = new ArrayList<>();
		try {
			con = DbConnect.getConnection();
			stmt = con.createStatement();
			
			String sql = "select * from customer where username = '"+email+"' and password = '"+password+"' ";
			rs = stmt.executeQuery(sql);
			
			//next is a boolean. returns true if the sql query is right
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String Email = rs.getString(3);
				String phone = rs.getString(4);
				String userName = rs.getString(5);
				String pass = rs.getString(6);
				
				Customer c = new Customer(id, name, Email , phone, userName, pass);
				
				//passing customer object to arraylist object
				cus.add(c);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}

		return cus;
	}
	
	public static boolean insertCustomer(String name, String email, String phone, String username, String password) {
		try {
			con = DbConnect.getConnection();
			stmt = con.createStatement();
			
			String sql = "insert into supermarket.customer values (0, '"+name+"', '"+email+"', '"+phone+"', '"+username+"', '"+password+"') ";
			
			int rs = stmt.executeUpdate(sql);
			if(rs > 0) { 
				isSuccess = true;
			}else {
				isSuccess = false;
			}	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	
	public static Customer userUpdate(String id, String name, String email, String phone, String username) {
		Customer customer = null;
		try {
			con = DbConnect.getConnection();
			stmt = con.createStatement();
			String sql = "update supermarket.customer set customer_name = '"+name+"', email = '"+email+"', phone = '"+phone+"', username = '"+username+"' where customer_id = '"+id+"' ";
			//int affected = stmt.executeUpdate(sql);
			
			/*for the auto logout*/
			rs = stmt.executeQuery(sql);
			//if(rs.next();
			
			//if(affected > 0) {
			if(rs.next()) {
				customer = new Customer(); 
				customer.setId(rs.getInt(1)); 
				customer.setName(rs.getString(2));
				customer.setEmail(rs.getString(3));
				customer.setPhone(rs.getString(4));
				customer.setUsername(rs.getString(5));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	public static boolean updateCustomer(String id, String name, String email, String phone, String username) {	
		try {
			con = DbConnect.getConnection();
			stmt = con.createStatement();
			
			String sql = "update supermarket.customer set customer_name = '"+name+"', email = '"+email+"', phone = '"+phone+"', username = '"+username+"' where customer_id = '"+id+"' ";
			
			int rs = stmt.executeUpdate(sql);	
			if(rs > 0) { 
				isSuccess = true;
			}else {
				isSuccess = false;
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;	
	}
	
	
	public static boolean confirmPass(String id, String curPassword) {	
		try {
			con = DbConnect.getConnection();
			stmt = con.createStatement();
			String sql = "select * from supermarket.customer where password = '"+curPassword+"' and customer_id = '"+id+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				isSuccess = true;
			}else {
				isSuccess = false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	public static Customer userUpdatePass(String id, String password) {
		Customer customer = null;
		try {
			con = DbConnect.getConnection();
			stmt = con.createStatement();
			String sql = "update supermarket.customer set password = '"+password+"' where customer_id = '"+id+"' ";
			int affected = stmt.executeUpdate(sql);
			
			if(affected > 0) {
				customer = new Customer(); 
				customer.setId(rs.getInt(1)); 
				customer.setName(rs.getString(2));
				customer.setEmail(rs.getString(3));
				customer.setPhone(rs.getString(4));
				customer.setUsername(rs.getString(5));
				customer.setPassword(rs.getString(6));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return customer;
	}	
	
	
	
	
	public static boolean deleteCustomer(String id) {	
		int convertedId = Integer.parseInt(id);
		
		try {
			con = DbConnect.getConnection();
			stmt = con.createStatement();
			
			String sql = "delete from supermarket.customer where customer_id = '"+convertedId+"' ";
			
			int rs = stmt.executeUpdate(sql);
			if(rs > 0) { 
				isSuccess = true;
			}else {
				isSuccess = false;
			}		
		}catch(Exception e) {
			System.out.println("unsuccessfull deletion");
			System.out.println(convertedId);
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	
	//data retrieve part
		public static List<Customer> getCustomerDetails(String id){
			int convertedId = Integer.parseInt(id);
			ArrayList<Customer> cus = new ArrayList<>();

			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				String sql = "select * from supermarket.customer where customer_id = '"+convertedId+"' ";
				rs = stmt.executeQuery(sql);

				while(rs.next()) {
					int cusId = rs.getInt(1);
					String name = rs.getString(2);
					String email = rs.getString(3);
					String phone = rs.getString(4);
					String username = rs.getString(5);
					String password = rs.getString(6);

					Customer c = new Customer(cusId, name, email, phone, username, password);
					cus.add(c);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return cus;	
		}
	
	
	public static boolean insertFeedback(String name, String email, String feedback) {
		try {
			con = DbConnect.getConnection();
			stmt = con.createStatement();
			
			String sql = "insert into supermarket.feedback values (0, '"+name+"', '"+email+"', '"+feedback+"') ";
			
			int rs = stmt.executeUpdate(sql);
			if(rs > 0) { 
				isSuccess = true;
			}else {
				isSuccess = false;
			}	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}

}
