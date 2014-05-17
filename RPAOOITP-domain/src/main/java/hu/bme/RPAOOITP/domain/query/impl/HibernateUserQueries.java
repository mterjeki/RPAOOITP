
package hu.bme.RPAOOITP.domain.query.impl;

import hu.bme.RPAOOITP.domain.io.LoginDTO;
import hu.bme.RPAOOITP.domain.io.RegistrationDTO;
import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.model.CompetencyToUser;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.domain.query.UserQueries;
import hu.bme.RPAOOITP.domain.query.exception.LoginException;
import hu.bme.RPAOOITP.domain.query.exception.RegistrationException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class HibernateUserQueries extends AbstractHibernateBaseRPAOOITPQueries implements UserQueries {
	
	@Override
	public User login( final LoginDTO loginDTO ) throws LoginException {
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
					user.getCompany();
					return user;
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
		
		User user = new User( username, email, password, registrationDTO.getLastName(), registrationDTO.getFirstName(), null );
		persistEntity( user );
	}
	
	@Override
	public void modifyUser( final RegistrationDTO registrationDTO ) throws RegistrationException {
		UUID id = registrationDTO.getId();
		User user = findEntityById( User.class, id );
		user.setEmail( registrationDTO.getEmail() );
		user.setFirstName( registrationDTO.getFirstName() );
		user.setLastName( registrationDTO.getLastName() );
		user.setPassword( registrationDTO.getPassword() );
		user.setUsername( registrationDTO.getUsername() );
		
		List<User> users = loadAll( User.class );
		
		for (User tmpuser : users) {
			if (tmpuser.getId().equals( id )) {
				continue;
			}
			
			String userUsername = tmpuser.getUsername();
			String userEmail = tmpuser.getEmail();
			
			if (userUsername.equals( user.getUsername() )) {
				throw new RegistrationException( "Username is already registered, please select another one" );
			}
			
			if (userEmail.equals( user.getEmail() )) {
				throw new RegistrationException( "Email is already registered, only one account is possible for one user" );
			}
		}
		
		mergeEntity( user );
	}
	
	@Override
	public Set<Competency> findAllCompetencyByUser( final User user ) {
		User loggedInUser = findEntityById( User.class, user.getId() );
		List<CompetencyToUser> competenciesToUser = loadAll( CompetencyToUser.class );
		
		Set<Competency> competencies = Sets.newHashSet();
		for (CompetencyToUser competencyToUserTMP : competenciesToUser) {
			if (competencyToUserTMP.getUser().equals( loggedInUser )) {
				competencies.add( competencyToUserTMP.getCompetency() );
			}
		}
		
		return competencies;
	}
	
	@Override
	public List<User> findAllUser() {
		List<User> loadAll = loadAll( User.class );
		List<User> users = Lists.newArrayList();
		
		for (User user : loadAll) {
			if (user.getCompany() == null) {
				users.add( user );
			}
		}
		
		return users;
	}
	
	@Override
	public List<User> findAllUser( final Company company ) {
		List<User> loadAll = loadAll( User.class );
		List<User> users = Lists.newArrayList();
		
		for (User user : loadAll) {
			if (user.getCompany().getId().equals( company.getId() )) {
				user.getPresences();
				users.add( user );
			}
		}
		
		return users;
	}
	
}
