
package hu.bme.RPAOOITP.domain.query;

import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.query.impl.HibernateBaseQueries;

import java.util.Set;

import com.google.inject.ImplementedBy;

@ImplementedBy( HibernateBaseQueries.class )
public interface BaseQueries {
	
	void createCompetency( final Competency competency );
	
	Set<Competency> findAllCompetencies();
	
}
