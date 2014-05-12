
package hu.bme.RPAOOITP.domain.query.impl;

import hu.bme.RPAOOITP.domain.model.AbstractProjectUnit;
import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.Process;
import hu.bme.RPAOOITP.domain.model.Project;
import hu.bme.RPAOOITP.domain.model.Task;
import hu.bme.RPAOOITP.domain.query.ProjectQueries;

import java.util.List;

public class HibernateProjectQueries extends AbstractHibernateBaseRPAOOITPQueries implements ProjectQueries {
	
	@Override
	public List<Project> findProjectsByCompany( final Company company ) {
		List<Project> projects = loadAll( Project.class );
		
		for (Project project : projects) {
			if (project.getOwner().equals( company )) {
				projects.add( project );
			}
		}
		
		return projects;
	}
	
	@Override
	public List<Process> findProcessesByProject( final Project project ) {
		List<Process> processes = loadAll( Process.class );
		
		for (Process process : processes) {
			if (process.getProject().equals( project )) {
				processes.add( process );
			}
		}
		
		return processes;
	}
	
	@Override
	public List<Task> findTasksByProcess( final Process process ) {
		List<Task> tasks = loadAll( Task.class );
		
		for (Task task : tasks) {
			if (task.getProcess().equals( process )) {
				tasks.add( task );
			}
		}
		
		return tasks;
	}
	
	@Override
	public List<AbstractProjectUnit> findAllProjectUnitsByCompany( final Company company ) {
		List<AbstractProjectUnit> projectUnits = loadAll( AbstractProjectUnit.class );
		
		for (AbstractProjectUnit projectUnit : projectUnits) {
			if (projectUnit.equals( company )) {
				projectUnits.add( projectUnit );
			}
		}
		
		return projectUnits;
	}
}
