package org.ethan.trial.security.auth;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.ethan.trial.security.remote.exception.RemoteAuthenticationException;

public final class UserManager {
	
	private static final Logger log = Logger.getLogger(UserManager.class);
	
	private volatile static UserManager userManager;
	
	private UserManager() {
		initSecurityManager();
	}
	
	public static UserManager getInstance() {
		if (userManager == null) {
			synchronized (UserManager.class) {
				userManager = new UserManager();
			}
		} 
		return userManager;
	}
	
	private void initSecurityManager() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
	}
	
	public Subject login(String username, char[] password) throws RemoteAuthenticationException {
		Subject user = new Subject.Builder().buildSubject();
		user.getSession();
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			user.login(token);
		} catch (AuthenticationException e) {
			log.warn("Login failed: " + e);
			throw new RemoteAuthenticationException(e.getMessage());
		} 
		
		log.info("User [" + user.getPrincipal() + "] logged in successfully.");
		return user;
	}
	
	public Subject getUser(String sessionId) {
		Subject existUser = new Subject.Builder().sessionId(sessionId).buildSubject();
		if(existUser.isAuthenticated()) {
			return existUser;
		} else {
			log.info("Session expired.");
			return null;
		}
	}
}
