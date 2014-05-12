
package hu.bme.RPAOOITP.business;

import hu.bme.RPAOOITP.business.impl.PlanningServiceImpl;
import hu.bme.RPAOOITP.domain.io.PlannedResult;
import hu.bme.RPAOOITP.domain.model.Company;

import java.util.List;

import com.google.inject.ImplementedBy;

@ImplementedBy( PlanningServiceImpl.class )
public interface PlanningService {
	
	List<PlannedResult> doPlanningForCompany( final Company company );
	
}
