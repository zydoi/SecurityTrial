package org.ethan.trial.security.remote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RemoteServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		System.out.println("RMI server started.");

		LocateRegistry.createRegistry(1099);
		System.out.println("RMI registry created.");

		RmiLogin login = new RmiLoginImpl();
		Naming.rebind("//localhost/login", login);
		System.out.println("Server bound in registry");
	}
}
