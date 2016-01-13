package io.rrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.NotFoundException;

import io.rrs.exception.AppException;
import io.rrs.model.Owner;
import io.rrs.util.DBUtil;

public class OwnerDAO {

	public Owner findOne(String restName) throws AppException {
		Owner owner = null;
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM owner WHERE REST_NAME=?");
			ps.setString(1, restName);
			rs = ps.executeQuery();
			if (rs.next()) {
				owner = new Owner();
				owner.setRestName(rs.getString("REST_NAME"));
				owner.setEmail(rs.getString("EMAIL"));
				owner.setPhone(rs.getString("PHONE"));
				owner.setLocation(rs.getString("LOCATION"));
				owner.setOpenDays(rs.getString("OPEN_DAYS"));
				owner.setOpenTime(rs.getString("OPEN_TIME"));
				owner.setAutoAssign(rs.getBoolean("AUTO_ASSIGN"));
			} else {
				throw new NotFoundException("Owner not found");
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
		return owner;
	}

	public void update(String restName) throws AppException {
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("UPDATE rrs.owner SET REST_NAME = 'UpdatedName' WHERE REST_NAME = ?");
			ps.setString(1, restName);
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
