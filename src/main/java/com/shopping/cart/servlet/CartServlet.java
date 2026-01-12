package com.shopping.cart.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;



import com.shopping.cart.model.Cart;

@WebServlet("/add-to-cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()) {
			ArrayList<Cart> cartList = new ArrayList<Cart>();
			
			int id = Integer.parseInt(request.getParameter("id"));
			Cart ct = new Cart();
			ct.setId(id);
			ct.setQuantity(1);
			
			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list =  (ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cart_list == null) {
				cartList.add(ct);
				session.setAttribute("cart-list", cartList);
				response.sendRedirect("index.jsp");
			} else {
				cartList = cart_list;
				boolean exist = false;
				
				for(Cart c1 : cart_list) {
					if(c1.getId() == id) {
						exist = true;
						out.println("<h3 style='color:crimson; text-align:center'>Item already exist in Cart.<a href='cart.jsp'>Go to Cart Page</a></h3>");
					}
				}
				
				if(!exist) {
					cartList.add(ct);
					response.sendRedirect("index.jsp");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
 		}
 	}

}
