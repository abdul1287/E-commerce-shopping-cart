package com.shopping.cart.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.shopping.cart.connection.DbConn;
import com.shopping.cart.dao.UserDao;
import com.shopping.cart.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user-register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String name = request.getParameter("Register-name");
			String email = request.getParameter("Register-email");
			String pass = request.getParameter("Register-password");
			String conPass = request.getParameter("Confirm-password");
			HttpSession session = request.getSession();

			try {
				if (pass.equals(conPass)) {
					UserDao userDao = new UserDao(DbConn.getConnection());
					User user = userDao.userRegister(name, email, pass);
					if (user != null) {
						session.setAttribute("regis-success", "User Registered Successfully... ");
						response.sendRedirect("register.jsp");
					} else {
						session.setAttribute("regis-failed", "User Already Registered !");
						response.sendRedirect("register.jsp");
					}
				} else {
					session.setAttribute("regis-invalid", "Password and Confirm-Password didn't Matched !");
					response.sendRedirect("register.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}