package org.ethan.trial.security.remote;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.ethan.trial.security.dto.User;


public class RemoteSessionImpl extends UnicastRemoteObject implements RemoteSession, Unreferenced {

	private static final long serialVersionUID = -2078292654064882830L;

	private static final Logger logger = Logger.getLogger(RemoteSessionImpl.class);
	
	private User user;
	
	protected RemoteSessionImpl() throws RemoteException {
		super(1199);
	}

	public RemoteSessionImpl(Subject subject) throws RemoteException {
		super(1199);
		this.user = new User(subject.getPrincipal().toString());
	}


	@Override
	public void unreferenced() {
		try {
			unexportObject(this, true);
		} catch (NoSuchObjectException e) {
			logger.error(e);
		}
	}

	@Override
	public void logout() throws RemoteException {
		try {
			unexportObject(this, true);
		} catch (NoSuchObjectException e) {
			logger.error(e);
		}
	}

	@Override
	public User getUser() throws RemoteException {
		return user;
	}

}
