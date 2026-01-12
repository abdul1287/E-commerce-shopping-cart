package com.shopping.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shopping.cart.model.User;

public class UserDao {

	private Connection conn;
	private String query;
	private PreparedStatement ps, ps1, ps2, ps3, ps4;
	private ResultSet rs, rs1, rs2;

	public UserDao(Connection conn) {
		this.conn = conn;
	}

	public User userRegister(String name, String email, String password) {
		User u2 = null;
		try {
			query = "insert into user (name, email, password) values(?,?,?)";
			ps = conn.prepareStatement("select name from user where email=?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				return u2;
			} else {
				ps1 = conn.prepareStatement(query);
				ps1.setString(1, name);
				ps1.setString(2, email);
				ps1.setString(3, password);
				int count = ps1.executeUpdate();

				ps2 = conn.prepareStatement("select * from user");
				rs1 = ps2.executeQuery();
				if (rs1.next()) {
					u2 = new User();
					u2.setId(rs1.getInt("id"));
					u2.setName(rs1.getString("name"));
					u2.setEmail(rs1.getString("email"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u2;
	}

	public User userLogin(String email, String password) {
		User u1 = null;
		try {
			query = "select * from user where email=? and password=?";
			ps3 = conn.prepareStatement(query);
			ps3.setString(1, email);
			ps3.setString(2, password);
			ResultSet rs2 = ps3.executeQuery();

			if (rs2.next()) {
				u1 = new User();
				u1.setId(rs2.getInt("id"));
				u1.setName(rs2.getString("name"));
				u1.setEmail(rs2.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u1;
	}

	public boolean forgotPass(String email, String pass) {
		boolean b = false;

		try {

			query = "update user set password=? where email=?";
			String query1 = "select email from user where email=?";
			ps = conn.prepareStatement(query1);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				ps1 = conn.prepareStatement(query);
				ps1.setString(1, pass);
				ps1.setString(2, email);
				int i = ps1.executeUpdate();

				if (i > 0) {
					b = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return b;
	}

}
