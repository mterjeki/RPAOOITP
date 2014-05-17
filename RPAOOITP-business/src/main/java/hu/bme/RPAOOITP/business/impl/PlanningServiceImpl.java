
package hu.bme.RPAOOITP.business.impl;

import hu.bme.RPAOOITP.business.PlanningService;
import hu.bme.RPAOOITP.domain.io.PlannedResult;
import hu.bme.RPAOOITP.domain.model.AbstractProjectUnit;
import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.model.Presence;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.domain.query.ProjectQueries;
import hu.bme.RPAOOITP.domain.query.UserQueries;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.Inject;

public class PlanningServiceImpl implements PlanningService {
	
	@Inject
	private UserQueries userQueries;
	
	@Inject
	private ProjectQueries projectQueries;
	
	private final Map<User, Set<Competency>> userToCompetency = Maps.newHashMap();
	private final Map<User, List<Presence>> userToPresence = Maps.newHashMap();
	private final Map<AbstractProjectUnit, User> possibleUserToProjectUnit = Maps.newHashMap();
	
	@Override
	public List<PlannedResult> doPlanningForCompany( final Company company ) {
		List<User> findAllUser = userQueries.findAllUser( company );
		
		for (User user : findAllUser) {
			userToCompetency.put( user, userQueries.findAllCompetencyByUser( user ) );
			userToPresence.put( user, user.getPresences() );
		}
		
		List<AbstractProjectUnit> projectUnits =
			projectQueries.findAllProjectUnitsByCompany( company );
		
		for (AbstractProjectUnit projectUnit : projectUnits) {
			for (User user : findAllUser) {
				Set<Competency> competencies = userToCompetency.get( user );
				for (Competency competency : competencies) {
					for (Competency comp2 : projectUnit.getCompetencies()) {
						if (comp2.getId().equals( competency.getId() )) {
							possibleUserToProjectUnit.put( projectUnit, user );
						}
					}
					
				}
			}
		}
		
		return Lists.newArrayList();
	}
	
}
