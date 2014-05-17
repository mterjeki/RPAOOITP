
package hu.bme.RPAOOITP.domain.query;

import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.domain.query.impl.HibernateCompetencyQueries;

import java.util.List;

import com.google.inject.ImplementedBy;

@ImplementedBy( HibernateCompetencyQueries.class )
public interface CompetencyQueries {
	
	void createCompetency( final Competency competency );
	
	List<Competency> findAllCompetencies();
	
	void addCompetency( final User user, final Competency competency );
	
	void removeCompetency( final User user, final Competency competency );
	
}
