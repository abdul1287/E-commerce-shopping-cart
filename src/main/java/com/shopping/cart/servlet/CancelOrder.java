package com.shopping.cart.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.shopping.cart.connection.DbConn;
import com.shopping.cart.dao.OrderDao;
import com.shopping.cart.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cancel-order")
public class CancelOrder extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try(PrintWriter out = resp.getWriter()) {
			
			String id = req.getParameter("id");
			  User us = (User)req.getSession().getAttribute("us");	
			  
			  if(id != null && us != null) {
				  OrderDao orderDao = new OrderDao(DbConn.getConnection());
				  orderDao.cancelOrder(Integer.parseInt(id));
			  }
			  resp.sendRedirect("orders.jsp");
		} catch (Exception e) {
			e.printStackTrace();
 		}
		
 	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 		doGet(req, resp);
	}
}
