<%@page import="java.text.DecimalFormat"%>
<%@page import="com.shopping.cart.connection.DbConn"%>
<%@page import="com.shopping.cart.dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.shopping.cart.model.User"%>
<%@page import="com.shopping.cart.model.Cart"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
User us = (User) request.getSession().getAttribute("us");
if (us != null) {
	request.setAttribute("us", us);
}

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProducts = null;
if (cart_list != null) {
	ProductDao pDao = new ProductDao(DbConn.getConnection());
	cartProducts = pDao.getCartProducts(cart_list);
	double total = pDao.getTotalPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total", total);
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Cart Pages</title>
<%@include file="includes/head.jsp"%>
<style type="text/css">
.table tbody td {
	vertical-align: middle;
}

/* Updated Code */
.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
	color: #ffbc5e !important; /* This applies the yellow color */
}

.btn-yellow {
	background-color: #ffbc5e !important;
	border-color: #ffbc5e !important;
	color: #000 !important;
	font-weight: 500;
}

.form-inline button[type="submit"] {
	margin-left: 20px;
}
</style>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price : $ ${ (total > 0)?dcf.format(total):0 }</h3>
			<a class="mx-3 btn btn-yellow" href="check-out"><i
				class="fa fa-check-circle" aria-hidden="true"></i> Check Outs</a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (Cart c : cartProducts) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td>$ <%=dcf.format(c.getPrice())%></td>
					<td>
						<form action="order-now" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-input">
							<div class="form-group d-flex justify-content-between w-50">
								<a class="btn btn-sm btn-decre"
									href="inc-dec?action=dec&id=<%=c.getId()%>"><i
									class="fas fa-minus-square"></i></a> <input type="text"
									name="quantity" class="form-control w-50"
									value="<%=c.getQuantity()%>" readonly> <a
									class="btn btn-sm btn-incre"
									href="inc-dec?action=inc&id=<%=c.getId()%>"><i
									class="fas fa-plus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-yellow btn-sm">Buy
								Now</button>
						</form>
					</td>
					<td><a class="btn btn-sm btn-danger"
						href="remove?id=<%=c.getId()%>">Remove</a></td>
				</tr>
				<%
				}
				}
				%>

			</tbody>
		</table>
	</div>

	<div style="padding-top: 420px;">
		<%@include file="includes/foot.jsp"%>
	</div>
</body>
</html>