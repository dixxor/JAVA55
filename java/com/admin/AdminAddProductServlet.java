package com.admin;

import java.io.IOException;

import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class AdminAddProductServlet
 */
@WebServlet("/AdminAddProductServlet")
@MultipartConfig
public class AdminAddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String name = request.getParameter("name");
		String size = request.getParameter("size");
		String price = request.getParameter("price");
		Part filePart = request.getPart("image");
		String category = request.getParameter("category");
		int cId = Integer.parseInt(category);
		double Price = Double.parseDouble(price);
		
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	    
		AdminDBUtil.ProductAdd(name, size, Price, fileName, cId);
	    
	    String uploadDir = "C:\\Users\\HP\\eclipse-workspace\\Supermarket\\src\\main\\webapp\\images\\products";

	    Path filePath = Path.of(uploadDir, fileName);

	    try (InputStream fileContent = filePart.getInputStream()) {
	    	Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
	    response.sendRedirect("addproduct.jsp");	
	    System.out.println(name);	
	    System.out.println(size);
	    System.out.println(price);
	    System.out.println(fileName);
	    System.out.println(category);
	}
		
}


