package com.vtiger.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	Connection connection;

	public void getDbConnnection(String url, String username, String password) throws SQLException {
		try {
			Driver driver = new Driver();

			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
		}
	}

	public void getDbConnnection() throws SQLException {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");

		} catch (Exception e) {
		}
	}

	public void closeDbConnection() {
		try {
			connection.close();
		} catch (Exception e) {
		}
	}

	public ResultSet executeSelectQuery(String query) {
		ResultSet result = null;
		try {
			Statement statement = connection.createStatement();
			result = statement.executeQuery(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int executeNonSelectQuery(String query) {
		int result = 0;
		try {
			Statement statement = connection.createStatement();
			result = statement.executeUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
