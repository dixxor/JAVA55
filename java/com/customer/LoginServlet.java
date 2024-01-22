package com.customer;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//for the else part to show the alert
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
				
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		/*
		boolean isTrue = CustomerDbUtil.validateLogin(email, password);
		if(isTrue) {
			List<Customer> cusDetails = CustomerDbUtil.getCustomer(email, password);
			request.setAttribute("cusDetails", cusDetails);
			request.getSession().setAttribute("authenticate", cusDetails);
			
			RequestDispatcher dis = request.getRequestDispatcher("profile.jsp");
			dis.forward(request, response);
		}
		else {
			//system.out displays in the console. out object displays in the browser
			out.println("<script type='text/javascript'>");
			out.println("alert('Your username or password is incorrect');");
			out.println("location='login.jsp'");
			out.println("</script>"); 
		}
		*/
		
		Customer customer = CustomerDbUtil.userLogin(email, password);
		if(customer != null) {
			List<Customer> cusDetails = CustomerDbUtil.getCustomer(email, password);
			request.setAttribute("cusDetails", cusDetails);
			
			request.getSession().setAttribute("authenticate", customer);
			response.sendRedirect("home.jsp");
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Your username or password is incorrect');");
			out.println("location='login.jsp'");
			out.println("</script>"); 
		}
	}

}
