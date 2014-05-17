
package hu.bme.RPAOOITP.ejb;

import hu.bme.RPAOOITP.domain.io.PlannedResult;
import hu.bme.RPAOOITP.domain.model.Company;

import java.util.List;

public interface PlanningManager {
	
	List<PlannedResult> doPlanningForCompany( final Company company );
	
}
