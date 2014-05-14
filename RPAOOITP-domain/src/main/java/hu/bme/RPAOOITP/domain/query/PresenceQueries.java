
package hu.bme.RPAOOITP.domain.query;

import hu.bme.RPAOOITP.domain.io.LoggedInUserDTO;
import hu.bme.RPAOOITP.domain.model.Presence;
import hu.bme.RPAOOITP.domain.query.impl.HibernatePresenceQueries;

import java.util.List;

import com.google.inject.ImplementedBy;

@ImplementedBy( HibernatePresenceQueries.class )
public interface PresenceQueries {
	
	List<Presence> findAllPresenceToUser( final LoggedInUserDTO user );
	
	void addNewPresenceToUser( final LoggedInUserDTO user, final Presence presence );
	
	void modifyPresenceToUser( final Presence newPresence );
	
}
