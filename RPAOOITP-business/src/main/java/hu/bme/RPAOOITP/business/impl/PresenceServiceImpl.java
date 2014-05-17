
package hu.bme.RPAOOITP.business.impl;

import hu.bme.RPAOOITP.business.PresenceService;
import hu.bme.RPAOOITP.domain.model.Presence;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.domain.query.PresenceQueries;

import java.util.List;

import com.google.inject.Inject;

public class PresenceServiceImpl implements PresenceService {
	
	@Inject
	private PresenceQueries presenceQueries;
	
	@Override
	public List<Presence> findAllPresenceToUser( final User user ) {
		return presenceQueries.findAllPresenceToUser( user );
	}
	
	@Override
	public void addNewPresenceToUser( final User user, final Presence presence ) {
		presenceQueries.addNewPresenceToUser( user, presence );
	}
	
	@Override
	public void modifyPresenceToUser( final Presence newPresence ) {
		presenceQueries.modifyPresenceToUser( newPresence );
	}
	
}
