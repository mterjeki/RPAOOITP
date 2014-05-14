
package hu.bme.RPAOOITP.domain.query;

import hu.bme.RPAOOITP.domain.io.LoggedInUserDTO;
import hu.bme.RPAOOITP.domain.io.LoginDTO;
import hu.bme.RPAOOITP.domain.io.RegistrationDTO;
import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.query.exception.LoginException;
import hu.bme.RPAOOITP.domain.query.exception.RegistrationException;
import hu.bme.RPAOOITP.domain.query.impl.HibernateUserQueries;

import java.util.Set;

import com.google.inject.ImplementedBy;

@ImplementedBy( HibernateUserQueries.class )
public interface UserQueries {
	
	LoggedInUserDTO login( final LoginDTO loginDTO ) throws LoginException;
	
	void registerUser( final RegistrationDTO registrationDTO ) throws RegistrationException;
	
	void modifyUser( final RegistrationDTO registrationDTO ) throws RegistrationException;
	
	void addCompetencies( final LoggedInUserDTO user, final Competency competency );
	
	Set<Competency> findAllCompetencyByUser( final LoggedInUserDTO user );
	
}
