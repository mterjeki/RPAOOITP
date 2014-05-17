
package hu.bme.RPAOOITP.ejb.internal;

import hu.bme.RPAOOITP.business.ProjectService;
import hu.bme.RPAOOITP.domain.model.AbstractProjectUnit;
import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.Process;
import hu.bme.RPAOOITP.domain.model.Project;
import hu.bme.RPAOOITP.domain.model.Task;
import hu.bme.RPAOOITP.ejb.ProjectManager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local( ProjectManager.class )
public class ProjectManagerEJB extends AbstractManagerEJB implements ProjectManager {
	
	@Override
	public void addProjectToCompany( final Project project, final Company company ) {
		buildInjector().getInstance( ProjectService.class ).addProjectToCompany( project, company );
	}
	
	@Override
	public List<AbstractProjectUnit> findAllProjectUnitsByCompany( final Company company ) {
		return buildInjector().getInstance( ProjectService.class ).findAllProjectUnitsByCompany( company );
	}
	
	@Override
	public void removeProject( final Project project ) {
		buildInjector().getInstance( ProjectService.class ).removeProject( project );
	}
	
	@Override
	public void addProcessToProject( final Project project, final Process process ) {
		buildInjector().getInstance( ProjectService.class ).addProcessToProject( project, process );
	}
	
	@Override
	public void removeProcess( final Process process ) {
		buildInjector().getInstance( ProjectService.class ).removeProcess( process );
	}
	
	@Override
	public void addTaskToProcess( final Process process, final Task task ) {
		buildInjector().getInstance( ProjectService.class ).addTaskToProcess( process, task );
	}
	
	@Override
	public void removeTask( final Task task ) {
		buildInjector().getInstance( ProjectService.class ).removeTask( task );
	}
	
	@Override
	public void addTaskToProject( final Project project, final Task task ) {
		buildInjector().getInstance( ProjectService.class ).addTaskToProject( project, task );
	}
	
}
