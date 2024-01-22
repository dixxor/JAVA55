package com.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteCustomerServlet
 */
@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String id = request.getParameter("id");
		
		boolean isTrue = CustomerDbUtil.deleteCustomer(id);
		
		if(isTrue) {
			if(request.getSession().getAttribute("authenticate") != null) {
				request.getSession().removeAttribute("authenticate");
				response.sendRedirect("home.jsp");
			}else {
				response.sendRedirect("home.jsp");
			}
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Account not deleted. Try again later.');");
			out.println("location='profile.jsp'");
			out.println("</script>"); 
		}
	}
	
	
}


