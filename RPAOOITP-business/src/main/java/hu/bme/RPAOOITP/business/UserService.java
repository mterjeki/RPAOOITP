
package hu.bme.RPAOOITP.business;

import hu.bme.RPAOOITP.business.impl.UserServiceImpl;
import hu.bme.RPAOOITP.domain.io.LoggedInUserDTO;
import hu.bme.RPAOOITP.domain.io.LoginDTO;
import hu.bme.RPAOOITP.domain.io.RegistrationDTO;
import hu.bme.RPAOOITP.domain.query.exception.LoginException;
import hu.bme.RPAOOITP.domain.query.exception.RegistrationException;

import com.google.inject.ImplementedBy;

@ImplementedBy( UserServiceImpl.class )
public interface UserService {
	
	LoggedInUserDTO login( final LoginDTO loginDTO ) throws LoginException;
	
	void registerUser( final RegistrationDTO registrationDTO ) throws RegistrationException;
	
}
