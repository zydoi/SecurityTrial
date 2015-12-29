package org.ethan.trial.security.dto;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = -9188039221176598552L;
	
	private String username;
	
	public User(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
