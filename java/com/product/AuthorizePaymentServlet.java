package com.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.paypal.base.rest.PayPalRESTException;

@WebServlet("/AuthorizePaymentServlet")
public class AuthorizePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");				
		
		String subTotal = request.getParameter("total");
		String shipping = request.getParameter("shipping");
		String tax = request.getParameter("tax");
		String total = request.getParameter("payable");
		System.out.println("sub: "+subTotal+"ship: "+shipping+"tax:"+tax+"total:"+total);
		
		HttpSession sess = request.getSession();
		sess.setAttribute("name", name);
		sess.setAttribute("address", address);
		sess.setAttribute("phone", phone);
		sess.setAttribute("total", total);
		
		OrderDetail orderDetail = new OrderDetail(subTotal, total, shipping, tax);
		
		try {
			PaymentServices paymentServices = new PaymentServices(); 
			String approvalLink = paymentServices.authorizePayment(orderDetail, name); 
			
			response.sendRedirect(approvalLink);
			
		}catch(PayPalRESTException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
