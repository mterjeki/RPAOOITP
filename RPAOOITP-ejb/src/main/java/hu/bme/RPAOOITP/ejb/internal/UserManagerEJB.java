
package hu.bme.RPAOOITP.ejb.internal;

import hu.bme.RPAOOITP.business.UserService;
import hu.bme.RPAOOITP.domain.io.LoginDTO;
import hu.bme.RPAOOITP.domain.io.RegistrationDTO;
import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.domain.query.exception.LoginException;
import hu.bme.RPAOOITP.domain.query.exception.RegistrationException;
import hu.bme.RPAOOITP.ejb.UserManager;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local( UserManager.class )
public class UserManagerEJB extends AbstractManagerEJB implements UserManager {
	
	@Override
	public User login( final LoginDTO loginDTO ) throws LoginException {
		return buildInjector().getInstance( UserService.class ).login( loginDTO );
	}
	
	@Override
	public void registerUser( final RegistrationDTO registrationDTO ) throws RegistrationException {
		buildInjector().getInstance( UserService.class ).registerUser( registrationDTO );
	}
	
	@Override
	public void modifyUser( final RegistrationDTO registrationDTO ) throws RegistrationException {
		buildInjector().getInstance( UserService.class ).modifyUser( registrationDTO );
	}
	
	@Override
	public Set<Competency> findAllCompetencyByUser( final User user ) {
		return buildInjector().getInstance( UserService.class ).findAllCompetencyByUser( user );
	}
	
	@Override
	public List<User> findAllUser() {
		return buildInjector().getInstance( UserService.class ).findAllUser();
	}
	
}
