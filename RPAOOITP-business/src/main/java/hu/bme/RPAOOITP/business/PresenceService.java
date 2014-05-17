
package hu.bme.RPAOOITP.business;

import hu.bme.RPAOOITP.business.impl.PresenceServiceImpl;
import hu.bme.RPAOOITP.domain.model.Presence;
import hu.bme.RPAOOITP.domain.model.User;

import java.util.List;

import com.google.inject.ImplementedBy;

@ImplementedBy( PresenceServiceImpl.class )
public interface PresenceService {
	
	List<Presence> findAllPresenceToUser( final User user );
	
	void addNewPresenceToUser( final User user, final Presence presence );
	
	void modifyPresenceToUser( final Presence newPresence );
	
}
