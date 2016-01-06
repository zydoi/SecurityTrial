package org.ethan.trial.security.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.ethan.trial.security.entity.User;

public class UserDao {

	private static final Logger logger = Logger.getLogger(UserDao.class);

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";

	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/usertrial?useUnicode=true&characterEncoding=UTF-8";

	public boolean createUser(User user) {
		String sql = "insert into users (UserName, Password, Password_Salt) values (?, ?, ?)";

		try (Connection connection = getDBConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getHashedPassword());
			statement.setString(3, user.getPasswordSalt());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Failed to create user. ", e);
			return false;
		}
		logger.info("Successfully create user: " + user.getUserName());
		return true;
	}

	public boolean deleteUser(String userName) {
		String sql = "delete from users where UserName = ?";
		try (Connection connection = getDBConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, userName);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Failed to delete user. ", e);
			return false;
		} 
		logger.info("Successfully delete user: " + userName);
		return true;
	}
	
	private Connection getDBConnection() {
		Connection connection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(DB_CONNECTION, "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
