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

	public Reservation findOneByEmail(String email) throws AppException {
		Reservation reservation = null;
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// System.out.println("Found the reservation");
			ps = con.prepareStatement("SELECT * FROM reservation WHERE EMAIL=?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				reservation = new Reservation();
				reservation.setConfNumber(rs.getString("CONF_NUMBER"));
				reservation.setSize(rs.getInt("SIZE"));
				reservation.setTime(rs.getString("TIME"));
				reservation.setCustomerName(rs.getString("CUSTOMER_NAME"));
				reservation.setDate(rs.getString("DATE"));
				reservation.setTableNumber(rs.getInt("TABLE_NO"));
				reservation.setEmail(rs.getString("EMAIL"));
			} else {
				throw new NotFoundException("Reservation not found");
			}

		} catch (SQLException e) {
			/* System.out.println("findByOneEmail in ResDAO"); */
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

	public void update(Reservation res) throws AppException {
			Connection con = DBUtil.connect();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
			ps = con.prepareStatement(
					"UPDATE reservation SET CUSTOMER_NAME = ?, DATE = ?, TIME = ?, SIZE = ? WHERE EMAIL = ?");
			ps.setString(1, res.getCustomerName());
			ps.setString(2, res.getDate());
			ps.setString(3, res.getTime());
			ps.setInt(4, res.getSize());
			ps.setString(5, res.getEmail());
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
				res.setEmail(rs.getString("EMAIL"));
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
					"INSERT INTO rrs.reservation (SIZE, TIME, CUSTOMER_NAME, DATE, TABLE_NO, EMAIL) VALUES(?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			/* ps.setString(1, res.getConfNumber()); */

			ps.setInt(1, res.getSize());
			ps.setString(2, res.getTime());
			ps.setString(3, res.getCustomerName());
			ps.setString(4, res.getDate());
			ps.setInt(5, res.getTableNumber());
			ps.setString(6, res.getEmail());
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