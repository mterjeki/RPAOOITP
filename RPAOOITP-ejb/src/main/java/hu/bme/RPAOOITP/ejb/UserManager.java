
package hu.bme.RPAOOITP.ejb;

import hu.bme.RPAOOITP.domain.io.LoggedInUserDTO;
import hu.bme.RPAOOITP.domain.io.LoginDTO;
import hu.bme.RPAOOITP.domain.io.RegistrationDTO;
import hu.bme.RPAOOITP.domain.query.exception.LoginException;
import hu.bme.RPAOOITP.domain.query.exception.RegistrationException;

public interface UserManager {
	
	LoggedInUserDTO login( final LoginDTO loginDTO ) throws LoginException;
	
	void registerUser( final RegistrationDTO registrationDTO ) throws RegistrationException;
	
}
