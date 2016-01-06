package org.ethan.trial.security.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.ethan.trial.security.dto.UserDTO;


public interface RemoteSession extends Remote {
	
    void logout() throws RemoteException;
    
    UserDTO getUser() throws RemoteException;
    
    String getSessionId() throws RemoteException;
    
}
