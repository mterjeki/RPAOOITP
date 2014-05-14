
package hu.bme.RPAOOITP.business;

import hu.bme.RPAOOITP.business.impl.PresenceServiceImpl;
import hu.bme.RPAOOITP.domain.io.LoggedInUserDTO;
import hu.bme.RPAOOITP.domain.model.Presence;

import java.util.List;

import com.google.inject.ImplementedBy;

@ImplementedBy( PresenceServiceImpl.class )
public interface PresenceService {
	
	List<Presence> findAllPresenceToUser( final LoggedInUserDTO user );
	
	void addNewPresenceToUser( final LoggedInUserDTO user, final Presence presence );
	
	void modifyPresenceToUser( final Presence newPresence );
	
}
