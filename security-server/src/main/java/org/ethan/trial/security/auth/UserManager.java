package org.ethan.trial.security.auth;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm.SaltStyle;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.Factory;
import org.ethan.trial.security.dao.UserDao;
import org.ethan.trial.security.entity.User;
import org.ethan.trial.security.remote.exception.RemoteAuthenticationException;

public final class UserManager {

	private static final Logger log = Logger.getLogger(UserManager.class);

	private volatile static UserManager userManager;

	private UserDao userDao;

	private RandomNumberGenerator rng;

	private UserManager() {
		initSecurityManager();
		userDao = new UserDao();
		rng = new SecureRandomNumberGenerator();
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
		Collection<Realm> realms = ((RealmSecurityManager) securityManager).getRealms();
		JdbcRealm jdbcRealm = (JdbcRealm) realms.toArray()[0];
		jdbcRealm.setSaltStyle(SaltStyle.COLUMN);
		
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
		if (existUser.isAuthenticated()) {
			return existUser;
		} else {
			log.info("Session expired.");
			return null;
		}
	}

	public User createUser(String userName, String password) {
		ByteSource salt = rng.nextBytes();
		String hashedPassword = new Sha256Hash(password, salt.toBase64(), 1024).toBase64();
		User user = new User(userName, hashedPassword);
		user.setPasswordSalt(salt.toBase64());
		if (userDao.createUser(user)) {
			return user;
		}
		return null;
	}
	
	public boolean deleteUser(String userName) {
		return userDao.deleteUser(userName);
	}

	public static void main(String[] args) throws RemoteAuthenticationException, UnsupportedEncodingException {
		UserManager.getInstance().deleteUser("test");
		UserManager.getInstance().createUser("test", "123456");
		UserManager.getInstance().login("test", "123456".toCharArray());
	}
}
