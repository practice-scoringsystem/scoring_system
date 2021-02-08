package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {
	final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	final String DB_NAME ="java_db";
	final String DB_USER = "root";
	final String DB_PASSWORD = "hAtAhAtA6364";
	/* DBコネクション */
	public Connection con;
	/**
	 * コンストラクタ
	 */
	public ConnectionDAO() throws SQLException {
		setConnection();
	}

	public void setConnection() throws SQLException {

		try {
			String url = "jdbc:mysql://localhost:3306/java_db?serverTimezone=JST";
			Class.forName(DRIVER_NAME).newInstance();
			con = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("SQLにて問題が発生しました");
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("接続に失敗しました");
		}
	}

	/**
	 * クローズ
	 */
	public void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}
}
