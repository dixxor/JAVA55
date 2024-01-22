package com.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String feedback = request.getParameter("feedback");
		
        boolean isTrue = CustomerDbUtil.insertFeedback(name, email, feedback);
		
		if(isTrue) {
			RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
			dis.forward(request, response);
		}else {
//			RequestDispatcher dis = request.getRequestDispatcher("register.jsp");
//			dis.forward(request, response);
			out.println("<script type='text/javascript'>");
			out.println("alert('Unexpected error occured. Try again later!');");
			out.println("location='register.jsp'");
			out.println("</script>"); 
		}
	}

}
