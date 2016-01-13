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
				cust.setFirstName(rs.getString("FIRST_NAME"));
				cust.setLastName(rs.getString("LAST_NAME"));
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


	public Customer findOne(String fName) throws AppException, NotFoundException {
		Customer cust = null;
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM customers WHERE FIRST_NAME=?");
			ps.setString(1, fName);
			rs = ps.executeQuery();
			if (rs.next()) {
				cust = new Customer();
				cust.setFirstName(rs.getString("FIRST_NAME"));
				cust.setLastName(rs.getString("LAST_NAME"));
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

	public void update(String fName) throws AppException {
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(
					"UPDATE rrs.customers SET FIRST_NAME = 'Updated', LAST_NAME = 'Name' WHERE FIRST_NAME = ?");
			ps.setString(1, fName);
			ps.executeUpdate();

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
