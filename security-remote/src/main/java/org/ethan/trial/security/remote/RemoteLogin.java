package org.ethan.trial.security.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteLogin extends Remote {
	
	RemoteSession login(String username, char[] password) throws RemoteException; 
}
