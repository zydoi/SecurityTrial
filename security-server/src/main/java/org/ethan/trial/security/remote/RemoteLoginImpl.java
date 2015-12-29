package org.ethan.trial.security.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.ethan.trial.security.auth.UserManager;
import org.ethan.trial.security.remote.exception.RemoteAuthenticationException;

public class RemoteLoginImpl extends UnicastRemoteObject implements RemoteLogin {

	private static final Logger log = Logger.getLogger(RemoteLoginImpl.class);
	
	protected RemoteLoginImpl() throws RemoteException {
		super(1199);
	}

	private static final long serialVersionUID = 6398675990893815399L;

	@Override
	public RemoteSession login(String username, char[] password) throws RemoteException {
		UserManager userManager = UserManager.getInstance();
		try {
			Subject user = userManager.login(username, password);
			return new RemoteSessionImpl(user);
		} catch (RemoteAuthenticationException e) {
			log.info("Login failed: " + e);
			return null;
		}
	}
	
	
}
