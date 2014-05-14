
package hu.bme.RPAOOITP.ejb.internal;

import hu.bme.RPAOOITP.business.BaseService;
import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.ejb.CompetencyManager;

import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local( CompetencyManager.class )
public class CompetencyManagerEJB extends AbstractManagerEJB implements CompetencyManager {
	
	@Override
	public void createCompetency( final Competency competency ) {
		buildInjector().getInstance( BaseService.class ).createCompetency( competency );
	}
	
	@Override
	public Set<Competency> findAllCompetencies() {
		return buildInjector().getInstance( BaseService.class ).findAllCompetencies();
	}
	
}
