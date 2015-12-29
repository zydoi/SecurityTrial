package org.ethan.security.demo.remote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class RemoteUtils {

	@SuppressWarnings("unchecked")
	public static <T extends Remote> T getRemoteService(String name) {
		T service = null;
		try {
			service = (T) Naming.lookup("//localhost/" + name);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO
			System.out.println("Cannot establish connection: " + e);
		}
		return service;
	}
}
