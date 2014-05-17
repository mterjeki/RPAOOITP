
package hu.bme.RPAOOITP.ejb;

import hu.bme.RPAOOITP.domain.model.Presence;
import hu.bme.RPAOOITP.domain.model.User;

import java.util.List;

public interface PresenceManager {
	
	List<Presence> findAllPresenceToUser( final User user );
	
	void addNewPresenceToUser( final User user, final Presence presence );
	
	void modifyPresenceToUser( final Presence newPresence );
	
}
