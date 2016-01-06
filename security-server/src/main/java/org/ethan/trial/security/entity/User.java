package org.ethan.trial.security.entity;

public class User {

	private String userName;
	
	private String passwordSalt;
	
	private String hashedPassword;
	
	public User(String userName, String hashedPassword) {
		this.userName = userName;
		this.hashedPassword = hashedPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
}
