package org.ethan.trial.security.server.auth;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class UserLoginAuth {

	private static final Logger log = Logger.getLogger(UserLoginAuth.class);

	public static void main(String[] args) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		log.info("Setup shiro security manager.");
		Subject currentUser = SecurityUtils.getSubject();

		Session session = currentUser.getSession();
		session.setAttribute("someKey", "aValue");
		String value = (String) session.getAttribute("someKey");
		if (value.equals("aValue")) {
			log.info("Retrieved the correct value! [" + value + "]");
		}

		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
			token.setRememberMe(true);
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				log.info("There is no user with username of " + token.getPrincipal());
			} catch (IncorrectCredentialsException ice) {
				log.info("Password for account " + token.getPrincipal() + " was incorrect!");
			} catch (LockedAccountException lae) {
				log.info("The account for username " + token.getPrincipal() + " is locked.  "
						+ "Please contact your administrator to unlock it.");
			}

		}
		log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

		Subject user = new Subject.Builder().buildSubject();
		Serializable sessionId = (String) user.getSession().getId();
		UsernamePasswordToken token = new UsernamePasswordToken("guest", "guest");
		// token.setRememberMe(true);
		user.login(token);

		Subject existUser = new Subject.Builder().sessionId(sessionId).buildSubject();
		log.info(existUser.getPrincipal());
		
	}
}
