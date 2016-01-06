package org.ethan.trial.security.dto;

import java.io.Serializable;

public class RoleDTO implements Serializable {
	
	private static final long serialVersionUID = -4902249903138137544L;

	private String roleName;
	
	private String description;

	public RoleDTO(String roleName, String description) {
		super();
		this.roleName = roleName;
		this.description = description;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getDescription() {
		return description;
	}
}
