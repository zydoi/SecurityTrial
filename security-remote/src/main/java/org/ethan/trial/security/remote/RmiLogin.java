package org.ethan.trial.security.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiLogin extends Remote {
	
	RmiSession login(String username, String password) throws RemoteException; 
}
