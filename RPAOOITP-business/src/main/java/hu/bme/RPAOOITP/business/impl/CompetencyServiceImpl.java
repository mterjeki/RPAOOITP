
package hu.bme.RPAOOITP.business.impl;

import hu.bme.RPAOOITP.business.CompetencyService;
import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.domain.query.CompetencyQueries;

import java.util.List;

import com.google.inject.Inject;

public class CompetencyServiceImpl implements CompetencyService {
	
	@Inject
	private CompetencyQueries competencyQueries;
	
	@Override
	public void createCompetency( final Competency competency ) {
		competencyQueries.createCompetency( competency );
	}
	
	@Override
	public List<Competency> findAllCompetencies() {
		return competencyQueries.findAllCompetencies();
	}
	
	@Override
	public void addCompetency( final User user, final Competency competency ) {
		competencyQueries.addCompetency( user, competency );
	}
	
	@Override
	public void removeCompetency( final User user, final Competency competency ) {
		competencyQueries.removeCompetency( user, competency );
	}
}
