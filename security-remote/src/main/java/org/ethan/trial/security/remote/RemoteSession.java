package org.ethan.trial.security.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.ethan.trial.security.dto.User;


public interface RemoteSession extends Remote {
	
    void logout() throws RemoteException;
    
    User getUser() throws RemoteException;
    
    String getSessionId() throws RemoteException;
    
}
