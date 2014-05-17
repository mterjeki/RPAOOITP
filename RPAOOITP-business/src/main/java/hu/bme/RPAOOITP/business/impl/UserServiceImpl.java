
package hu.bme.RPAOOITP.business.impl;

import hu.bme.RPAOOITP.business.UserService;
import hu.bme.RPAOOITP.domain.io.LoginDTO;
import hu.bme.RPAOOITP.domain.io.RegistrationDTO;
import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.domain.query.UserQueries;
import hu.bme.RPAOOITP.domain.query.exception.LoginException;
import hu.bme.RPAOOITP.domain.query.exception.RegistrationException;

import java.util.List;
import java.util.Set;

import com.google.inject.Inject;

public class UserServiceImpl implements UserService {
	
	@Inject
	private UserQueries userQueries;
	
	@Override
	public User login( final LoginDTO loginDTO ) throws LoginException {
		return userQueries.login( loginDTO );
	}
	
	@Override
	public void registerUser( final RegistrationDTO registrationDTO ) throws RegistrationException {
		userQueries.registerUser( registrationDTO );
	}
	
	@Override
	public void modifyUser( final RegistrationDTO registrationDTO ) throws RegistrationException {
		userQueries.modifyUser( registrationDTO );
	}
	
	@Override
	public Set<Competency> findAllCompetencyByUser( final User user ) {
		return userQueries.findAllCompetencyByUser( user );
	}
	
	@Override
	public List<User> findAllUser() {
		return userQueries.findAllUser();
	}
	
}
