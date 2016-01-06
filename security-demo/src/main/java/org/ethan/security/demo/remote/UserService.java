package org.ethan.security.demo.remote;

import java.rmi.RemoteException;

import org.ethan.trial.security.dto.UserDTO;
import org.ethan.trial.security.remote.RemoteLogin;
import org.ethan.trial.security.remote.RemoteSession;

public class UserService {

	private RemoteLogin remoteLogin;

	private static UserService service = new UserService();

	private RemoteSession session;

	private UserService() {
	}

	public static UserService getInstance() {
		return service;
	}

	public boolean login(String username, char[] password) {

		if (remoteLogin == null) {
			remoteLogin = RemoteUtils.getRemoteService("login");
		}
		try {
			session = remoteLogin.login(username, password);
			if (session != null) {
				return true;
			}
		} catch (RemoteException e) {
			e.printStackTrace(); // TODO
		}
		return false;
	}

	public UserDTO getCurrentUser() {
		UserDTO user = null;
		try {
			 user = session.getUser();
		} catch (RemoteException e) {
			e.printStackTrace(); // TODO
		}
		return user;
	}
	
	public void logout() {
		
	}
	
	
}
