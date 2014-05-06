
package hu.bme.RPAOOITP.base.queries;

import hu.bme.RPAOOITP.base.Competency;
import hu.bme.RPAOOITP.base.User;

public interface BasePartyQueries {
	
	void addCompetencyToParty( final User party, final Competency competency );
	
}
