package com.shopping.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopping.cart.model.Order;
import com.shopping.cart.model.Product;


public class OrderDao {

	private Connection conn;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public OrderDao(Connection conn) {
		this.conn = conn;
	}
	
	public boolean insertOrder(Order model) {
		
		boolean result = false;
 		try {
 			query = "insert into orders(p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
 			ps = conn.prepareStatement(query);
			ps.setInt(1, model.getId());
			ps.setInt(2, model.getUid());
			ps.setInt(3, model.getQuantity());
			ps.setString(4, model.getDate());
			ps.executeUpdate();
			result = true;
  		} catch (Exception e) {
			e.printStackTrace();
 		}
		
		return result;
	}
	
	public List<Order> userOrders(int id) {
		List<Order> list = new ArrayList<Order>();
		
		try {
			
			query = "select * from orders where u_id=? order by orders.o_id desc";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				ProductDao productDao = new ProductDao(conn);
				int pid = rs.getInt("p_id");
				
				Product pr = productDao.getSingleProduct(pid);
				order.setOrderId(rs.getInt("o_id"));
				order.setId(pid);
				order.setName(pr.getName());
				order.setCategory(pr.getCategory());
				order.setPrice(pr.getPrice()*rs.getInt("o_quantity"));
				order.setQuantity(rs.getInt("o_quantity"));
				order.setDate(rs.getString("o_date"));
				list.add(order);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
 		}
		
		return list;
	}
	
	public void cancelOrder(int id) {
		try {
			query = "delete from orders where o_id=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
 		}
	}
}
