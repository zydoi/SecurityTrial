package org.ethan.trial.security.remote;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;

import org.apache.log4j.Logger;


public class RmiSessionImpl extends UnicastRemoteObject implements RmiSession, Unreferenced {

	private static final Logger logger = Logger.getLogger(RmiSessionImpl.class);
	
	protected RmiSessionImpl() throws RemoteException {
		super(1199);
	}

	private static final long serialVersionUID = -2078292654064882830L;

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

}
