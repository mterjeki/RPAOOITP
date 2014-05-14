
package hu.bme.RPAOOITP.business.impl;

import hu.bme.RPAOOITP.business.BaseService;
import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.query.BaseQueries;

import java.util.Set;

import com.google.inject.Inject;

public class BaseServiceImpl implements BaseService {
	
	@Inject
	private BaseQueries baseQueries;
	
	@Override
	public void createCompetency( final Competency competency ) {
		baseQueries.createCompetency( competency );
	}
	
	@Override
	public Set<Competency> findAllCompetencies() {
		return baseQueries.findAllCompetencies();
	}
	
}
