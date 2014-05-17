
package hu.bme.RPAOOITP.ejb.internal;

import hu.bme.RPAOOITP.business.PresenceService;
import hu.bme.RPAOOITP.domain.model.Presence;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.ejb.PresenceManager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local( PresenceManager.class )
public class PresenceManagerEJB extends AbstractManagerEJB implements PresenceManager {
	
	@Override
	public List<Presence> findAllPresenceToUser( final User user ) {
		return buildInjector().getInstance( PresenceService.class ).findAllPresenceToUser( user );
	}
	
	@Override
	public void addNewPresenceToUser( final User user, final Presence presence ) {
		buildInjector().getInstance( PresenceService.class ).addNewPresenceToUser( user, presence );
	}
	
	@Override
	public void modifyPresenceToUser( final Presence newPresence ) {
		buildInjector().getInstance( PresenceService.class ).modifyPresenceToUser( newPresence );
	}
	
}
