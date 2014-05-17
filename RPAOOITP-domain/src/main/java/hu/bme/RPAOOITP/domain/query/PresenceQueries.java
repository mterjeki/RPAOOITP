
package hu.bme.RPAOOITP.domain.query;

import hu.bme.RPAOOITP.domain.model.Presence;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.domain.query.impl.HibernatePresenceQueries;

import java.util.List;

import com.google.inject.ImplementedBy;

@ImplementedBy( HibernatePresenceQueries.class )
public interface PresenceQueries {
	
	List<Presence> findAllPresenceToUser( final User user );
	
	void addNewPresenceToUser( final User user, final Presence presence );
	
	void modifyPresenceToUser( final Presence newPresence );
	
}
