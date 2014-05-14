
package hu.bme.RPAOOITP.ejb;

import hu.bme.RPAOOITP.domain.model.Competency;

import java.util.Set;

public interface CompetencyManager {
	
	@Deprecated
	void createCompetency( final Competency competency );
	
	Set<Competency> findAllCompetencies();
	
}
