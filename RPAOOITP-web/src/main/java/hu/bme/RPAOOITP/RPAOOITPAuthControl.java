
package hu.bme.RPAOOITP;

import com.vaadin.cdi.access.AccessControl;

public class RPAOOITPAuthControl extends AccessControl {
	
	public void login( final String username, final String password ) {
		
	}
	
	public void logout() {
		
	}
	
	@Override
	public boolean isUserSignedIn() {
		return false;
	}
	
	@Override
	public boolean isUserInRole( final String role ) {
		return true;
	}
	
	@Override
	public String getPrincipalName() {
		return null;
	}
	
}
