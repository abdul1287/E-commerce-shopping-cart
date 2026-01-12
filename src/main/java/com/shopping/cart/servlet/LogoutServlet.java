package com.shopping.cart.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/log-out")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {

			HttpSession session = request.getSession();
			session.removeAttribute("us");

			HttpSession session2 = request.getSession();
			session2.setAttribute("logout-Msg", "Logout Successfully..");
			response.sendRedirect("login.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
