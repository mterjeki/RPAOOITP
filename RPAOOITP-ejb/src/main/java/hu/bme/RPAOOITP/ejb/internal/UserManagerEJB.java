
package hu.bme.RPAOOITP.ejb.internal;

import hu.bme.RPAOOITP.business.UserService;
import hu.bme.RPAOOITP.domain.io.LoggedInUserDTO;
import hu.bme.RPAOOITP.domain.io.LoginDTO;
import hu.bme.RPAOOITP.domain.io.RegistrationDTO;
import hu.bme.RPAOOITP.domain.query.exception.LoginException;
import hu.bme.RPAOOITP.domain.query.exception.RegistrationException;
import hu.bme.RPAOOITP.ejb.UserManager;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local( UserManager.class )
public class UserManagerEJB extends AbstractManagerEJB implements UserManager {
	
	@Override
	public LoggedInUserDTO login( final LoginDTO loginDTO ) throws LoginException {
		return buildInjector().getInstance( UserService.class ).login( loginDTO );
	}
	
	@Override
	public void registerUser( final RegistrationDTO registrationDTO ) throws RegistrationException {
		buildInjector().getInstance( UserService.class ).registerUser( registrationDTO );
	}
	
}
