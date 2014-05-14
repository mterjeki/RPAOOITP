
package hu.bme.RPAOOITP.domain.query.impl;

import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.query.BaseQueries;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

public class HibernateBaseQueries extends AbstractHibernateBaseRPAOOITPQueries implements BaseQueries {
	
	@Override
	public void createCompetency( final Competency competency ) {
		persistEntity( competency );
	}
	
	@Override
	public Set<Competency> findAllCompetencies() {
		List<Competency> loadAll = loadAll( Competency.class );
		return Sets.newHashSet( loadAll );
	}
	
}
