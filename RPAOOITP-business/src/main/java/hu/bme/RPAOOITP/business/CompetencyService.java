
package hu.bme.RPAOOITP.business;

import hu.bme.RPAOOITP.business.impl.CompetencyServiceImpl;
import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.model.User;

import java.util.List;

import com.google.inject.ImplementedBy;

@ImplementedBy( CompetencyServiceImpl.class )
public interface CompetencyService {
	
	void createCompetency( final Competency competency );
	
	List<Competency> findAllCompetencies();
	
	void addCompetency( final User user, final Competency competency );
	
	void removeCompetency( final User user, final Competency competency );
	
}
