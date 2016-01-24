package io.rrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import io.rrs.exception.AppException;
import io.rrs.model.Customer;
import io.rrs.util.DBUtil;

public class CustomerDAO {

	public List<Customer> findAllCust() throws AppException {
		List<Customer> customers = new ArrayList<Customer>();
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM customers");
			rs = ps.executeQuery();
			while (rs.next()) {
				Customer cust = new Customer();
				cust.setCustomerName(rs.getString("NAME"));
				cust.setEmail(rs.getString("EMAIL"));
				cust.setPhone(rs.getString("PHONE"));
				customers.add(cust);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customers;
	}


	public Customer findOne(String email) throws AppException, NotFoundException {
		Customer cust = null;
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM customers WHERE EMAIL=?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				cust = new Customer();
				cust.setCustomerName(rs.getString("NAME"));
				cust.setEmail(rs.getString("EMAIL"));
				cust.setPhone(rs.getString("PHONE"));
			} else {
				throw new NotFoundException("Customer not found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cust;
	}

	public void update(Customer cust) throws AppException {
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("UPDATE rrs.customers SET EMAIL = ?, PHONE = ? WHERE NAME = ?");
			ps.setString(1, cust.getEmail());
			ps.setString(2, cust.getPhone());
			System.out.println(cust.getPhone());
			ps.setString(3, cust.getCustomerName());
			int result = ps.executeUpdate();
			System.out.println(result);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
