package com.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateCustomerPassServlet
 */
@WebServlet("/UpdateCustomerPassServlet")
public class UpdateCustomerPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String curPassword = request.getParameter("curPassword");
		String conPassword = request.getParameter("conPassword");
		
		if(Objects.equals(password, conPassword)) {
			boolean isTrue = CustomerDbUtil.confirmPass(id, curPassword);
			
			if(isTrue) {
				Customer updatedCustomer = CustomerDbUtil.userUpdatePass(id, password);
				if(updatedCustomer != null) {
					request.getSession().setAttribute("authenticate", updatedCustomer);
					
					RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
					dis.forward(request, response);
				}else {
					out.println("<script type='text/javascript'>");
					out.println("alert('Failed to update password');");
					out.println("location='profile.jsp'");
					out.println("</script>");
				}
			}else {
				out.println("<script type='text/javascript'>");
				out.println("alert('current password is incorrect');");
				out.println("location='profile.jsp'");
				out.println("</script>"); 		
			}
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('New password does not match');");
			out.println("location='profile.jsp'");
			out.println("</script>"); 
		}
			
	}
	

}
