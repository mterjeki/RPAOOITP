
package hu.bme.RPAOOITP.ejb;

import hu.bme.RPAOOITP.domain.io.LoggedInUserDTO;
import hu.bme.RPAOOITP.domain.io.LoginDTO;
import hu.bme.RPAOOITP.domain.io.RegistrationDTO;
import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.query.exception.LoginException;
import hu.bme.RPAOOITP.domain.query.exception.RegistrationException;

import java.util.Set;

public interface UserManager {
	
	LoggedInUserDTO login( final LoginDTO loginDTO ) throws LoginException;
	
	void registerUser( final RegistrationDTO registrationDTO ) throws RegistrationException;
	
	void modifyUser( final RegistrationDTO registrationDTO ) throws RegistrationException;
	
	void addCompetency( final LoggedInUserDTO user, final Competency competency );
	
	Set<Competency> findAllCompetencyByUser( final LoggedInUserDTO user );
	
}
