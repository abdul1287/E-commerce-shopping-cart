<%@page import="com.shopping.cart.model.User"%>
<%@page import="com.shopping.cart.model.Cart"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User us = (User) request.getSession().getAttribute("us");
if (us != null) {
	response.sendRedirect("index.jsp");
}

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Shopping-cart Login</title>
<%@include file="includes/head.jsp"%>
</head>
<body
	style="background: linear-gradient(135deg, #007bff 0%, #00c6ff 50%, #20c493 100%);">
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">
				<i class="fa fa-user-circle" aria-hidden="true"></i> User Login
			</div>


			<%
			String logFailMsg = (String) session.getAttribute("log-fail");
			if (logFailMsg != null) {
			%>
			<div class="alert alert-danger" role="alert"><%=logFailMsg%></div>
			<%
			session.removeAttribute("log-fail");
			}
			%>

			<%
			String withoutLogMsg = (String) session.getAttribute("Login-error");
			if (withoutLogMsg != null) {
			%>
			<div class="alert alert-danger" role="alert"><%=withoutLogMsg%></div>
			<%
			session.removeAttribute("Login-error");
			}
			%>

			<%
			String logoutMsg = (String) session.getAttribute("logout-Msg");
			if (logoutMsg != null) {
			%>
			<div class="alert alert-success" role="alert"><%=logoutMsg%></div>
			<%
			session.removeAttribute("logout-Msg");
			}
			%>

			<!-- Forgot Password message -->

			<%
			String successMsg = (String) session.getAttribute("success-forgot");
			if (successMsg != null) {
			%>
			<div class="alert alert-success" role="alert"><%=successMsg%></div>
			<%
			}
			session.removeAttribute("success-forgot");
			%>

			<div class="card-body">
				<form action="user-login" method="post">
					<div class="form-group">
						<label>Email Address</label> <input type="email"
							class="form-control" name="Login-email"
							placeholder="Enter Your Email" required>
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password"
							class="form-control" name="Login-password" placeholder="********"
							required>
					</div>

					<a href="forgotPass.jsp">Forgot Password</a>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div style="padding-top: 160px;">
		<%@include file="includes/foot.jsp"%>
	</div>

</body>
</html>