
package hu.bme.RPAOOITP.ejb.internal;

import hu.bme.RPAOOITP.business.CompetencyService;
import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.ejb.CompetencyManager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local( CompetencyManager.class )
public class CompetencyManagerEJB extends AbstractManagerEJB implements CompetencyManager {
	
	@Override
	public void createCompetency( final Competency competency ) {
		buildInjector().getInstance( CompetencyService.class ).createCompetency( competency );
	}
	
	@Override
	public List<Competency> findAllCompetencies() {
		return buildInjector().getInstance( CompetencyService.class ).findAllCompetencies();
	}
	
	@Override
	public void removeCompetency( final User user, final Competency competency ) {
		buildInjector().getInstance( CompetencyService.class ).removeCompetency( user, competency );
	}
	
	@Override
	public void addCompetency( final User user, final Competency competency ) {
		buildInjector().getInstance( CompetencyService.class ).addCompetency( user, competency );
	}
	
}
