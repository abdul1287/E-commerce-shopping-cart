package com.shopping.cart.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.shopping.cart.connection.DbConn;
import com.shopping.cart.dao.UserDao;
import com.shopping.cart.model.User;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String email = request.getParameter("Login-email");
			String password = request.getParameter("Login-password");
			HttpSession session = request.getSession();

			try {
				UserDao userDao = new UserDao(DbConn.getConnection());
				User user = userDao.userLogin(email, password);
				if (user != null) {
					session.setAttribute("us", user);
					response.sendRedirect("index.jsp");
				} else {
					session.setAttribute("log-fail", "Invalid Email and Password !");
					response.sendRedirect("login.jsp");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
