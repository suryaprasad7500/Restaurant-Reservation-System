package io.rrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import io.rrs.exception.AppException;
import io.rrs.model.Reservation;
import io.rrs.util.DBUtil;

public class ReservationDAO {

	public Reservation findOne(int confNumber) throws AppException {
			Reservation reservation = null;
			Connection con = DBUtil.connect();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement("SELECT * FROM reservation WHERE CONF_NUMBER=?");
				ps.setInt(1, confNumber);
				rs = ps.executeQuery();
				if (rs.next()) {
					reservation = new Reservation();
					reservation.setConfNumber(rs.getString("CONF_NUMBER"));
					reservation.setSize(rs.getInt("SIZE"));
					reservation.setTime(rs.getString("TIME"));
					reservation.setCustomerName(rs.getString("CUSTOMER_NAME"));
					reservation.setDate(rs.getString("DATE"));
					reservation.setTableNumber(rs.getInt("TABLE_NO"));
				} else {
					throw new NotFoundException("Reservation not found");
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
			return reservation;
		}

	public void update(int confNumber) throws AppException {
			Connection con = DBUtil.connect();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
			ps = con.prepareStatement("UPDATE rrs.reservation SET CUSTOMER_NAME = 'UpdatedName' WHERE CONF_NUMBER = ?");
			ps.setInt(1, confNumber);
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

	public List<Reservation> findAllReservations() throws AppException {
			List<Reservation> reservations = new ArrayList<Reservation>();
			Connection con = DBUtil.connect();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement("SELECT * FROM reservation");
				rs = ps.executeQuery();
				while (rs.next()) {
					Reservation res = new Reservation();
					res.setConfNumber(rs.getString("CONF_NUMBER"));
					res.setSize(rs.getInt("SIZE"));
					res.setTime(rs.getString("TIME"));
					res.setCustomerName(rs.getString("CUSTOMER_NAME"));
					res.setDate(rs.getString("DATE"));
					res.setTableNumber(rs.getInt("TABLE_NO"));
					reservations.add(res);
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
			return reservations;
		}

	public void delete(int confNumber) throws AppException {
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("DELETE FROM rrs.reservation WHERE CONF_NUMBER = ?");
			ps.setInt(1, confNumber);
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

	public Reservation create(Reservation res) throws AppException {
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(
					"INSERT INTO rrs.reservation (CONF_NUMBER, SIZE, TIME, CUSTOMER_NAME, DATE, TABLE_NO) VALUES(?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, res.getConfNumber());
			ps.setInt(2, res.getSize());
			ps.setString(3, res.getTime());
			ps.setString(4, res.getCustomerName());
			ps.setString(5, res.getDate());
			ps.setInt(6, res.getTableNumber());
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
		return res;
	}

}