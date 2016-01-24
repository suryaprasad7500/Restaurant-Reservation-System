package io.rrs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/rrs";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC driver loaded");
		} catch (ClassNotFoundException e) {
			System.err.println("Loading JDBC Driver failed bud!");
			e.printStackTrace();
		}
	}

	public static Connection connect() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			System.out.println("Cannot connect!");
			e.printStackTrace();
		}
		return con;
	}
}
