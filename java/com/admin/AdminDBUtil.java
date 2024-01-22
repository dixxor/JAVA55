package com.admin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.customer.Customer;
import com.customer.DbConnect;

 public class AdminDBUtil {
	 
	 private static boolean isSuccess;
		private static Connection con = null;
		private static Statement stmt = null;
		private static ResultSet rs = null;
		
		public static boolean validateAdminLogin(String email, String password) {
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				
				String sql = "select * from admin where email = '"+email+"' and password = '"+password+"' ";
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
		
		public static Admin AdminLogin(String email, String password) {
			Admin admin = null;
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				
				String sql = "select * from supermarket.admin where email = '"+email+"' and password = '"+password+"' ";
				rs = stmt.executeQuery(sql);
				
				//next is a boolean. returns true if the sql query is right
				if(rs.next()) {
					admin = new Admin(); //creating a new instance
					admin.setAdminId(rs.getInt(1)); //customer.setId(rs.getInt("customer_id");
					admin.setEmail(rs.getString(2));
					admin.setPassword(rs.getString(3));
					
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return admin;
		}
		
		//returns a customer array list
		public static List<Admin> getAdmin(String email, String password){
		
			ArrayList<Admin> adm = new ArrayList<>();
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				
				String sql = "select * from admin where username = '"+email+"' and password = '"+password+"' ";
				rs = stmt.executeQuery(sql);
				
				//next is a boolean. returns true if the sql query is right
				while(rs.next()) {
					int id = rs.getInt(1);
					String Email = rs.getString(2);
					String pass = rs.getString(3);
					
					Admin a = new Admin(id,  Email , pass);
					
					//passing customer object to arraylist object
					adm.add(a);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}

			return adm;
		}
		

		
		
		
		
		
		public static List<AdminP> getAllProduct(){
			//ArrayList<Product> product = new ArrayList<>();
			List<AdminP> product = new ArrayList<AdminP>();
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				String sql = "select * from supermarket.product";
				rs = stmt.executeQuery(sql);

				while(rs.next()) {
					AdminP row = new AdminP();
					row.setId(rs.getInt(1));
					row.setName(rs.getString(2));
					row.setSize(rs.getString(3));
					row.setPrice(rs.getDouble(4));
					row.setImage(rs.getString(5));
					row.setCategoryId(rs.getInt(6));

					product.add(row);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return product;
		}
		
		
		public static List<AdminOrder> getAllOrder(){
			//ArrayList<Product> product = new ArrayList<>();
			List<AdminOrder> product = new ArrayList<AdminOrder>();
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				String sql = "select * from supermarket.order";
				rs = stmt.executeQuery(sql);

				while(rs.next()) {
					AdminOrder row = new AdminOrder();
					row.setOrderId(rs.getInt(1));
					row.setCustomerId(rs.getInt(2));
					row.setProductId(rs.getInt(3));
					row.setOrderQty(rs.getInt(4));
					row.setOrderDate(rs.getDate(5));
					

					product.add(row);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return product;
		}
		
		
		public static List<AdminDelivery> getDeliveryDetails(){
			//ArrayList<Product> product = new ArrayList<>();
			List<AdminDelivery> product = new ArrayList<AdminDelivery>();
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				String sql = "select * from supermarket.shipping";
				rs = stmt.executeQuery(sql);

				while(rs.next()) {
					AdminDelivery row = new AdminDelivery();
					row.setOrderId(rs.getInt(1));
					row.setCustomerId(rs.getInt(2));
					row.setName(rs.getString(3));
					row.setPhone(rs.getString(4));
					row.setAddres(rs.getString(5));
					row.setTotal(rs.getString(6));
					

					product.add(row);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return product;
		}
		
		
		public static List<AdminFeedback> getFeedback(){
			//ArrayList<Product> product = new ArrayList<>();
			List<AdminFeedback> product = new ArrayList<AdminFeedback>();
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				String sql = "select * from supermarket.feedback";
				rs = stmt.executeQuery(sql);

				while(rs.next()) {
					AdminFeedback row = new AdminFeedback();
					row.setUserId(rs.getInt(1));
					row.setName(rs.getString(2));
					row.setEmail(rs.getString(3));
					row.setFeedback(rs.getString(4));
					

					product.add(row);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return product;
		}
		
		
		public static boolean ProductAdd(String name, String size, Double price, String fileName, int category) {
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				
				String sql = "insert into supermarket.product values (0, '"+name+"', '"+size+"', '"+price+"', '"+fileName+"', '"+category+"') ";
				
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
