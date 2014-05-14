
package hu.bme.RPAOOITP.business;

import hu.bme.RPAOOITP.business.impl.BaseServiceImpl;
import hu.bme.RPAOOITP.domain.model.Competency;

import java.util.Set;

import com.google.inject.ImplementedBy;

@ImplementedBy( BaseServiceImpl.class )
public interface BaseService {
	
	void createCompetency( final Competency competency );
	
	Set<Competency> findAllCompetencies();
	
}
