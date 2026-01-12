<%@page import="com.shopping.cart.model.User"%>
<%
User us7 = (User) request.getSession().getAttribute("us");
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-custom navbar-custom">
	<div class="container">
		<a class="navbar-brand" href="index.jsp"><i
			class="fa fa-shopping-cart" aria-hidden="true"></i> E-Commerce
			Shopping Cart</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp"><i class="fa fa-home" aria-hidden="true"></i>
						Home</a></li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp"><i
						class="fa fa-cart-plus" aria-hidden="true"></i> Cart<span
						class="badge badge-danger px-1">${ cart_list.size()}</span></a></li>
				<%
				if (us7 != null) {
				%>
				<li class="nav-item"><a class="nav-link" href="orders.jsp"><i
						class="fa fa-shopping-basket" aria-hidden="true"></i> Orders</a></li>
				<li class="nav-item"><a class="nav-link" href="log-out"><i
						class="fa fa-sign-out" aria-hidden="true"></i> Logout</a></li>
				<%
				} else {
				%>
				<li class="nav-item"><a class="nav-link" href="login.jsp"><i
						class="fa fa-user-circle" aria-hidden="true"></i> Login</a></li>
				<li class="nav-item"><a class="nav-link" href="register.jsp"><i
						class="fa fa-user-plus" aria-hidden="true"></i> Register</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
</nav>