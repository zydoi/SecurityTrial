package org.ethan.trial.security.remote.exception;

public class RemoteAuthenticationException extends Exception {


	private static final long serialVersionUID = 1005398833992527513L;

	public RemoteAuthenticationException(String message) {
		super(message);
	}
	
    public RemoteAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
