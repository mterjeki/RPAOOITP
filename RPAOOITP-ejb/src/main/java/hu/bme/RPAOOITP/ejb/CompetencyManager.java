
package hu.bme.RPAOOITP.ejb;

import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.model.User;

import java.util.List;

public interface CompetencyManager {
	
	@Deprecated
	void createCompetency( final Competency competency );
	
	List<Competency> findAllCompetencies();
	
	void addCompetency( final User user, final Competency competency );
	
	void removeCompetency( final User user, final Competency competency );
	
}
