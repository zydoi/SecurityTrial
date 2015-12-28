package org.ethan.trial.security.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RmiSession extends Remote {
	
    void logout() throws RemoteException;

}
