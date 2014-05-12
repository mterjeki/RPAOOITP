
package hu.bme.RPAOOITP.domain.query.impl;

import hu.bme.RPAOOITP.domain.io.LoggedInUserDTO;
import hu.bme.RPAOOITP.domain.io.LoginDTO;
import hu.bme.RPAOOITP.domain.io.RegistrationDTO;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.domain.query.UserQueries;
import hu.bme.RPAOOITP.domain.query.exception.LoginException;
import hu.bme.RPAOOITP.domain.query.exception.RegistrationException;

import java.util.List;

import com.google.common.base.Preconditions;

public class HibernateUserQueries extends AbstractHibernateBaseRPAOOITPQueries implements UserQueries {
	
	@Override
	public LoggedInUserDTO login( final LoginDTO loginDTO ) throws LoginException {
		Preconditions.checkNotNull( loginDTO );
		String usernameOrEmail = loginDTO.getUsernameOrEmail();
		String passwordIn = loginDTO.getPassword();
		
		List<User> users = loadAll( User.class );
		
		for (User user : users) {
			String username = user.getUsername();
			String email = user.getEmail();
			String password = user.getPassword();
			
			if (username.equals( usernameOrEmail ) || email.equals( usernameOrEmail )) {
				
				if (password.equals( passwordIn )) {
					return new LoggedInUserDTO( user.getId(), username, email );
				}
				else {
					throw new LoginException( "Incorrect password" );
				}
				
			}
			
		}
		
		throw new LoginException( "User not found with email and username" );
	}
	
	@Override
	public void registerUser( final RegistrationDTO registrationDTO ) throws RegistrationException {
		Preconditions.checkNotNull( registrationDTO );
		String email = registrationDTO.getEmail();
		String username = registrationDTO.getUsername();
		String password = registrationDTO.getPassword();
		
		List<User> users = loadAll( User.class );
		
		for (User user : users) {
			String userUsername = user.getUsername();
			String userEmail = user.getEmail();
			
			if (userUsername.equals( username )) {
				throw new RegistrationException( "Username is already registered, please select another one" );
			}
			
			if (userEmail.equals( email )) {
				throw new RegistrationException( "Email is already registered, only one account is possible for one user" );
			}
		}
		
		User user = new User( username, email, password );
		persistEntity( user );
	}
	
}
