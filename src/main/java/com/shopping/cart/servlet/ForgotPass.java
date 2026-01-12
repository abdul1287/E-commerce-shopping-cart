package com.shopping.cart.servlet;

import java.io.IOException;

import com.shopping.cart.connection.DbConn;
import com.shopping.cart.dao.UserDao;
import com.shopping.cart.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/forgot-pass")
public class ForgotPass extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("log-email");
		String pass = req.getParameter("new-pass");
		String conPass = req.getParameter("confirm-new-pass");
		HttpSession session = req.getSession();

		try {

			if (pass.equals(conPass)) {
				UserDao ud = new UserDao(DbConn.getConnection());
				boolean b = ud.forgotPass(email, pass);

				if (b) {
					session.setAttribute("success-forgot", "Password Changed Successfully..");
					resp.sendRedirect("login.jsp");
				} else {
					session.setAttribute("failForgot", "Email Doesn't Exist !");
					resp.sendRedirect("forgotPass.jsp");
				}
			} else {
				session.setAttribute("forgot-invalid", "Password and Confirm-Password didn't Matched !");
				resp.sendRedirect("forgotPass.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
