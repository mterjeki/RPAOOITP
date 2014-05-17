
package hu.bme.RPAOOITP.business.impl;

import hu.bme.RPAOOITP.business.ProjectService;
import hu.bme.RPAOOITP.domain.model.AbstractProjectUnit;
import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.Process;
import hu.bme.RPAOOITP.domain.model.Project;
import hu.bme.RPAOOITP.domain.model.Task;
import hu.bme.RPAOOITP.domain.query.ProjectQueries;

import java.util.List;

import com.google.inject.Inject;

public class ProjectServiceImpl implements ProjectService {
	
	@Inject
	private ProjectQueries projectQueries;
	
	@Override
	public void addProjectToCompany( final Project project, final Company company ) {
		projectQueries.addProjectToCompany( project, company );
	}
	
	@Override
	public void addProcessToProject( final Project project, final Process process ) {
		projectQueries.addProcessToProject( project, process );
	}
	
	@Override
	public void addTaskToProcess( final Process process, final Task task ) {
		projectQueries.addTaskToProcess( process, task );
	}
	
	@Override
	public List<AbstractProjectUnit> findAllProjectUnitsByCompany( final Company company ) {
		return projectQueries.findAllProjectUnitsByCompany( company );
	}
	
	@Override
	public void removeProject( final Project project ) {
		projectQueries.removeProject( project );
	}
	
	@Override
	public void removeProcess( final Process process ) {
		projectQueries.removeProcess( process );
	}
	
	@Override
	public void removeTask( final Task task ) {
		projectQueries.removeTask( task );
	}
	
	@Override
	public void addTaskToProject( final Project project, final Task task ) {
		projectQueries.addTaskToProject( project, task );
	}
	
}
