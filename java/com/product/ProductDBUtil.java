	package com.product;
import com.customer.DbConnect;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


	
	public class ProductDBUtil {
		private static boolean isSuccess;
		private static Connection con = null;
		private static Statement stmt = null;
		private static ResultSet rs = null;
		
		public static List<Product> getVegetables(){
			//ArrayList<Product> product = new ArrayList<>();
			List<Product> product = new ArrayList<Product>();
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				String sql = "select * from supermarket.product where category_id = 1";
				rs = stmt.executeQuery(sql);

				while(rs.next()) {
					Product row = new Product();
					row.setId(rs.getInt(1));
					row.setName(rs.getString(2));
					row.setSize(rs.getString(3));
					row.setPrice(rs.getDouble(4));
					row.setImage(rs.getString(5));

					product.add(row);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return product;
		}
		
		public static List<Product> getMeat(){
			//ArrayList<Product> product = new ArrayList<>();
			List<Product> product = new ArrayList<Product>();
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				String sql = "select * from supermarket.product where category_id = 2";
				rs = stmt.executeQuery(sql);

				while(rs.next()) {
					Product row = new Product();
					row.setId(rs.getInt(1));
					row.setName(rs.getString(2));
					row.setSize(rs.getString(3));
					row.setPrice(rs.getDouble(4));
					row.setImage(rs.getString(5));

					product.add(row);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return product;
		}
		
		
		public static List<Product> getbeverages(){
			//ArrayList<Product> product = new ArrayList<>();
			List<Product> product = new ArrayList<Product>();
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				String sql = "select * from supermarket.product where category_id = 3";
				rs = stmt.executeQuery(sql);

				while(rs.next()) {
					Product row = new Product();
					row.setId(rs.getInt(1));
					row.setName(rs.getString(2));
					row.setSize(rs.getString(3));
					row.setPrice(rs.getDouble(4));
					row.setImage(rs.getString(5));

					product.add(row);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return product;
		}
		
		public static List<Product> getgroceries(){
			//ArrayList<Product> product = new ArrayList<>();
			List<Product> product = new ArrayList<Product>();
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				String sql = "select * from supermarket.product where category_id = 4";
				rs = stmt.executeQuery(sql);

				while(rs.next()) {
					Product row = new Product();
					row.setId(rs.getInt(1));
					row.setName(rs.getString(2));
					row.setSize(rs.getString(3));
					row.setPrice(rs.getDouble(4));
					row.setImage(rs.getString(5));

					product.add(row);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return product;
		}
		
		public static List<Cart> getCartProducts(ArrayList<Cart> cartList){
			List<Cart> products = new ArrayList<Cart>();
			
			try {
				if(cartList.size()>0) {
					for(Cart item:cartList) {
						//for each product item, getting the related details from the db
						String sql = "select * from supermarket.product where product_id = '"+item.getId()+"' ";
						rs = stmt.executeQuery(sql);
						
						while(rs.next()) {
							Cart row = new Cart();
							row.setId(rs.getInt(1));
							row.setName(rs.getString(2));
							row.setPrice(rs.getDouble(4)*item.getQuantity());
							row.setImage(rs.getString(5));
							row.setQuantity(item.getQuantity());
							products.add(row); //added to the list to diaplay in the cart page
						}							
					}
				}			
			}catch(Exception e) {
				//System.out.println(e.getMessage());
				e.printStackTrace();
			}		
			return products;
		}
		
		public static double cartTotal(ArrayList<Cart> cartList) {
			double sum = 0;
			
			try {
				if(cartList.size()>0) {
					for(Cart item:cartList) {
						String sql = "select price from supermarket.product where product_id = '"+item.getId()+"' ";
						rs = stmt.executeQuery(sql);
						
						while(rs.next()) {
							sum += rs.getDouble("price")*item.getQuantity();
						}
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}		
			return sum;
		}
		
		public static int getorderId() {
			int orderId = 0;
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				String sql = "select * from supermarket.id";
				rs = stmt.executeQuery(sql);
				
				if(rs.next()) {
					orderId = rs.getInt(1);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return orderId;
		}
		
		public static boolean addOrder(Order order, int orderId) {
			isSuccess = false;
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				String sql = "insert into supermarket.order values ('"+orderId+"', '"+order.getCustomerId()+"', '"+order.getId()+"', '"+order.getOrderQty()+"',now())";
				int rs = stmt.executeUpdate(sql);
				
				if(rs > 0) {
					isSuccess = true;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return isSuccess;
		}
		
		public static void updateorderId(int incrementedId, int orderId) {
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				String sql = "update supermarket.id set id = '"+incrementedId+"' where id = '"+orderId+"' ";
				stmt.executeUpdate(sql);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static boolean shippingDetails(int orderId, int customerId, String name, String phone, String address, double total) {
			isSuccess = false;
			try {
				con = DbConnect.getConnection();
				stmt = con.createStatement();
				
				String sql = "insert into supermarket.shipping values ('"+orderId+"', '"+customerId+"', '"+name+"', '"+phone+"', '"+address+"', '"+total+"')";
				int rs = stmt.executeUpdate(sql);
				if(rs>0) {
					isSuccess = true;
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return isSuccess;
		}
		
		

	}
