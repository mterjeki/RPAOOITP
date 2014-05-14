
package hu.bme.RPAOOITP.ejb;

import hu.bme.RPAOOITP.domain.io.LoggedInUserDTO;
import hu.bme.RPAOOITP.domain.model.Presence;

import java.util.List;

public interface PresenceManager {
	
	List<Presence> findAllPresenceToUser( final LoggedInUserDTO user );
	
	void addNewPresenceToUser( final LoggedInUserDTO user, final Presence presence );
	
	void modifyPresenceToUser( final Presence newPresence );
	
}
