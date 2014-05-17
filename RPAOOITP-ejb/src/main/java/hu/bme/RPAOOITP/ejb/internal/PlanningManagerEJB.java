
package hu.bme.RPAOOITP.ejb.internal;

import hu.bme.RPAOOITP.business.PlanningService;
import hu.bme.RPAOOITP.domain.io.PlannedResult;
import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.ejb.PlanningManager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local( PlanningManager.class )
public class PlanningManagerEJB extends AbstractManagerEJB implements PlanningManager {
	
	@Override
	public List<PlannedResult> doPlanningForCompany( final Company company ) {
		return buildInjector().getInstance( PlanningService.class ).doPlanningForCompany( company );
	}
	
}
