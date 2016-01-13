package io.rrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import io.rrs.exception.AppException;
import io.rrs.model.Table;
import io.rrs.util.DBUtil;

public class TableDAO {
	
	public Table findOne(int tableNumber) throws AppException {
		Table tab = null;
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM seating WHERE TABLE_NUMBER=?");
			ps.setInt(1, tableNumber);
			rs = ps.executeQuery();
			if (rs.next()) {
				tab = new Table();
				tab.setTableNumber(rs.getInt("TABLE_NUMBER"));
				tab.setSize(rs.getInt("SIZE"));
				tab.setStatus(rs.getString("STATUS"));
				tab.setBookedSince(rs.getDate("BOOKED_SINCE"));
				tab.setConfNumber(rs.getInt("CONF_NUMBER"));
			} else {
				throw new NotFoundException("Table not found");
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
		return tab;
	}
	

	public void update(int tableNumber) throws AppException {
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("UPDATE rrs.seating SET STATUS = 'Updated' WHERE TABLE_NUMBER = ?");
			ps.setInt(1, tableNumber);
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


	public List<Table> findAllTables() throws AppException {
		List<Table> tables = new ArrayList<Table>();
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM seating");
			rs = ps.executeQuery();
			while (rs.next()) {
				Table tab = new Table();
				tab.setTableNumber(rs.getInt("TABLE_NUMBER"));
				tab.setConfNumber(rs.getInt("CONF_NUMBER"));
				tab.setSize(rs.getInt("SIZE"));
				tab.setBookedSince(rs.getDate("BOOKED_SINCE"));
				tables.add(tab);
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
		return tables;
	}
	}
