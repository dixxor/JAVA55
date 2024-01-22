package com.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class RemoveFromCartServlet
 */
@WebServlet("/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				
				try {
					String id = request.getParameter("id");
					if(id!=null) {
						ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
						if(cart_list!=null) {
							for(Cart c:cart_list) { //looping over each product
								if(c.getId() == Integer.parseInt(id)){
									cart_list.remove(cart_list.indexOf(c));
									break; //break outa loop after removing
								}
							}
							response.sendRedirect("cart.jsp");									
						}
					}else {
						response.sendRedirect("cart.jsp");
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
	}

}
