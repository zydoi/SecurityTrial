package org.ethan.trial.security.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiLoginImpl extends UnicastRemoteObject implements RmiLogin {

	protected RmiLoginImpl() throws RemoteException {
		super(1199);
	}

	private static final long serialVersionUID = 6398675990893815399L;

	@Override
	public RmiSession login(String username, String password) throws RemoteException {
		return null;
	}
	
	
}
