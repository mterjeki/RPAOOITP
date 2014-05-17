
package hu.bme.RPAOOITP.domain.query.impl;

import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.model.CompetencyToUser;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.domain.query.CompetencyQueries;

import java.util.List;

public class HibernateCompetencyQueries extends AbstractHibernateBaseRPAOOITPQueries implements CompetencyQueries {
	
	@Override
	public void createCompetency( final Competency competency ) {
		persistEntity( competency );
	}
	
	@Override
	public List<Competency> findAllCompetencies() {
		return loadAll( Competency.class );
	}
	
	@Override
	public void addCompetency( final User user, final Competency competency ) {
		User loggedInUser = findEntityById( User.class, user.getId() );
		Competency foundedCompetency = findEntityById( Competency.class, competency.getId() );
		CompetencyToUser competencyToUser = new CompetencyToUser( loggedInUser, foundedCompetency );
		persistEntity( competencyToUser );
	}
	
	@Override
	public void removeCompetency( final User user, final Competency competency ) {
		List<CompetencyToUser> competencyToUsers = loadAll( CompetencyToUser.class );
		CompetencyToUser competencyToUser = null;
		
		for (CompetencyToUser competencyToUserTMP : competencyToUsers) {
			
			if (competencyToUserTMP.getCompetency().equals( competency ) && competencyToUserTMP.getUser().getId().equals( user.getId() )) {
				competencyToUser = competencyToUserTMP;
				break;
			}
			
		}
		
		em.remove( competencyToUser );
		
	}
	
}
