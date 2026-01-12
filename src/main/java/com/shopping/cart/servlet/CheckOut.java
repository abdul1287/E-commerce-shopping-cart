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

@WebServlet("/check-out")
public class CheckOut extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(PrintWriter out = resp.getWriter()) {
			
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			// retrieve all cart products
			ArrayList<Cart> cart_list = (ArrayList<Cart>) req.getSession().getAttribute("cart-list");
			// User Authentication
			User us = (User) req.getSession().getAttribute("us");
			
			//Check us and cart list
			if(cart_list != null && us != null) {
				for(Cart c: cart_list) {
					Order order = new Order();
					order.setId(c.getId());
					order.setUid(us.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					
					//Instantiate the Dao class
					OrderDao dao = new OrderDao(DbConn.getConnection());
					boolean result = dao.insertOrder(order);
					
					if(!result) {
						break;
					}
				}
				
				cart_list.clear();
				resp.sendRedirect("orders.jsp");
				
			} else {
				if(us == null ) {
					resp.sendRedirect("login.jsp");
				} else {
					resp.sendRedirect("cart.jsp");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
 		}
 	}
}
