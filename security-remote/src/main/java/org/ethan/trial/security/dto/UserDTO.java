package org.ethan.trial.security.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = -9188039221176598552L;
	
	private String username;
	
	private List<RoleDTO> roles = new ArrayList<RoleDTO>();
	
	public UserDTO(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}
}
