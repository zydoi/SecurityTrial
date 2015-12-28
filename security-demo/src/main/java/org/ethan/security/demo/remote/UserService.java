package org.ethan.security.demo.remote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.ethan.trial.security.remote.RmiLogin;
import org.ethan.trial.security.remote.RmiSession;



public class UserService {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		RmiLogin server = (RmiLogin) Naming.lookup("//localhost/login");
		RmiSession session = server.login("", "");
	}
}
