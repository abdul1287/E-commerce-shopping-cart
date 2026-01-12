package com.shopping.cart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.shopping.cart.connection.DbConn;
import com.shopping.cart.dao.OrderDao;
import com.shopping.cart.model.Cart;
import com.shopping.cart.model.Order;
import com.shopping.cart.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/order-now")
public class OrderNow extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(PrintWriter out = resp.getWriter()) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			User us = (User) req.getSession().getAttribute("us");
			if(us != null) {
				String productId = req.getParameter("id");
				int quantity = Integer.parseInt(req.getParameter("quantity"));
				
				if(quantity <= 0) {
					quantity = 1;
				}
				
				Order orderModel = new Order();
				orderModel.setId(Integer.parseInt(productId));
				orderModel.setUid(us.getId());
				orderModel.setQuantity(quantity);
				orderModel.setDate(formatter.format(date));
				
				OrderDao dao = new OrderDao(DbConn.getConnection());
				boolean result = dao.insertOrder(orderModel);
				
				if(result) {
					ArrayList<Cart> cart_list = (ArrayList<Cart>) req.getSession().getAttribute("cart-list");
					if(cart_list != null) {
						for(Cart c: cart_list) {
							if(c.getId() == Integer.parseInt(productId)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}
					
					resp.sendRedirect("orders.jsp");
				} else {
					out.print("order failed");
				}
				
			} else {
				resp.sendRedirect("login.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
 		}
 	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
 	}
}
