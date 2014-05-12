
package hu.bme.RPAOOITP;

import hu.bme.RPAOOITP.domain.io.LoggedInUserDTO;
import hu.bme.RPAOOITP.domain.io.LoginDTO;
import hu.bme.RPAOOITP.domain.query.exception.LoginException;
import hu.bme.RPAOOITP.ejb.UserManager;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.vaadin.cdi.access.AccessControl;

@Alternative
public class RPAOOITPAuthControl extends AccessControl {
	
	@Inject
	private RPAOOITPSession session;
	
	@EJB
	private UserManager userManager;
	
	@PostConstruct
	public void init() {
		//		try {
		//			//			userService = (UserService) new InitialContext()
		//			//				.lookup( "java:global/RPAOOITP-app-0.0.1/RPAOOITP-web-0.0.1/UserManagerEJB!hu.bme.RPAOOITP.ejb.UserManager" );
		//		}
		//		catch (NamingException e) {
		//			e.printStackTrace();
		//		}
	}
	
	public void login( final LoginDTO loginDTO ) throws LoginException {
		LoggedInUserDTO loggedUser = userManager.login( loginDTO );
		session.setUserDTO( loggedUser );
	}
	
	public void logout() {
		session.setUserDTO( null );
	}
	
	@Override
	public boolean isUserSignedIn() {
		return session.getUserDTO() != null;
	}
	
	@Override
	public boolean isUserInRole( final String role ) {
		return true;
	}
	
	@Override
	public String getPrincipalName() {
		if (isUserSignedIn()) {
			return session.getUserDTO().getUsername();
		}
		
		return null;
	}
	
}
