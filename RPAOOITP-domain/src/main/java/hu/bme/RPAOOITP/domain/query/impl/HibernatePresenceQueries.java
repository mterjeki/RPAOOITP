
package hu.bme.RPAOOITP.domain.query.impl;

import hu.bme.RPAOOITP.domain.model.Presence;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.domain.query.PresenceQueries;

import java.util.List;

public class HibernatePresenceQueries extends AbstractHibernateBaseRPAOOITPQueries implements PresenceQueries {
	
	@Override
	public List<Presence> findAllPresenceToUser( final User user ) {
		User foundUser = findEntityById( User.class, user.getId() );
		return foundUser.getPresences();
	}
	
	@Override
	public void addNewPresenceToUser( final User user, final Presence presence ) {
		User foundUser = findEntityById( User.class, user.getId() );
		persistEntity( presence );
		foundUser.getPresences().add( presence );
	}
	
	@Override
	public void modifyPresenceToUser( final Presence newPresence ) {
		mergeEntity( newPresence );
	}
	
}
