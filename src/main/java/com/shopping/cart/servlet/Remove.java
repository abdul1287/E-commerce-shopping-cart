package com.shopping.cart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.shopping.cart.model.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/remove")
public class Remove extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   	   resp.setContentType("text/html;charset=UTF-8");
   	   
		try(PrintWriter out = resp.getWriter()) {
			String id = req.getParameter("id");
			if(id != null) {
				ArrayList<Cart> cart_list = (ArrayList<Cart>) req.getSession().getAttribute("cart-list");
				if(cart_list != null) {
					for(Cart c: cart_list) {
						if(c.getId() == Integer.parseInt(id)) {
							cart_list.remove(cart_list.indexOf(c));
							break;
						}
					}
					resp.sendRedirect("cart.jsp");
				}
			} else {
				resp.sendRedirect("cart.jsp");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
 		}
 	}
}
