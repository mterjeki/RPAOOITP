
package hu.bme.RPAOOITP.domain.query.impl;

import hu.bme.RPAOOITP.domain.model.AbstractProjectUnit;
import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.model.CompetencyToProcess;
import hu.bme.RPAOOITP.domain.model.CompetencyToProject;
import hu.bme.RPAOOITP.domain.model.CompetencyToTask;
import hu.bme.RPAOOITP.domain.model.Process;
import hu.bme.RPAOOITP.domain.model.Project;
import hu.bme.RPAOOITP.domain.model.Task;
import hu.bme.RPAOOITP.domain.query.ProjectQueries;

import java.util.List;

import com.google.common.collect.Lists;

public class HibernateProjectQueries extends AbstractHibernateBaseRPAOOITPQueries implements ProjectQueries {
	
	private void addCompetencyToProject( final Project task ) {
		List<CompetencyToProject> comptencies = loadAll( CompetencyToProject.class );
		List<Competency> competenciesLst = Lists.newArrayList();
		
		for (CompetencyToProject competency : comptencies) {
			if (competency.getProjectUnit().equals( task )) {
				competenciesLst.add( competency.getCompetency() );
			}
		}
		task.setCompetencies( competenciesLst );
	}
	
	private void addCompetencyToProcess( final Process task ) {
		List<CompetencyToProcess> comptencies = loadAll( CompetencyToProcess.class );
		List<Competency> competenciesLst = Lists.newArrayList();
		
		for (CompetencyToProcess competency : comptencies) {
			if (competency.getProjectUnit().equals( task )) {
				competenciesLst.add( competency.getCompetency() );
			}
		}
		task.setCompetencies( competenciesLst );
	}
	
	private void addCompetencyToTask( final Task task ) {
		List<CompetencyToTask> comptencies = loadAll( CompetencyToTask.class );
		List<Competency> competenciesLst = Lists.newArrayList();
		
		for (CompetencyToTask competency : comptencies) {
			if (competency.getProjectUnit().equals( task )) {
				competenciesLst.add( competency.getCompetency() );
			}
		}
		task.setCompetencies( competenciesLst );
	}
	
	@Override
	public List<AbstractProjectUnit> findAllProjectUnitsByCompany( final Company company ) {
		Company foundedCompany = findEntityById( Company.class, company.getId() );
		
		List<AbstractProjectUnit> projectUnits = Lists.newArrayList();
		
		projectUnits.addAll( loadAll( Project.class ) );
		projectUnits.addAll( loadAll( Process.class ) );
		projectUnits.addAll( loadAll( Task.class ) );
		
		List<AbstractProjectUnit> projectUnits2 = Lists.newArrayList( projectUnits );
		
		for (AbstractProjectUnit projectUnit : projectUnits2) {
			if (projectUnit.getCompany().equals( foundedCompany )) {
				
				if (projectUnit instanceof Project) {
					addCompetencyToProject( (Project) projectUnit );
				}
				if (projectUnit instanceof Process) {
					addCompetencyToProcess( (Process) projectUnit );
				}
				if (projectUnit instanceof Task) {
					addCompetencyToTask( (Task) projectUnit );
				}
				
				projectUnits.add( projectUnit );
			}
		}
		
		return projectUnits;
	}
	
	@Override
	public void addProjectToCompany( final Project project, final Company company ) {
		Company foundedCompany = findEntityById( Company.class, company.getId() );
		project.setCompany( foundedCompany );
		persistEntity( project );
		List<Competency> competencies = project.getCompetencies();
		
		for (Competency competency : competencies) {
			persistEntity( new CompetencyToProject( project, competency ) );
		}
	}
	
	@Override
	public void removeProject( final Project project ) {
		Project foundedProject = findEntityById( Project.class, project.getId() );
		
		List<CompetencyToProject> loadAllCompetencies = loadAll( CompetencyToProject.class );
		List<CompetencyToProject> deleteProcesses = Lists.newArrayList();
		
		for (CompetencyToProject competencyToProcess : loadAllCompetencies) {
			
			if (competencyToProcess.getProjectUnit().equals( foundedProject )) {
				deleteProcesses.add( competencyToProcess );
			}
		}
		
		for (CompetencyToProject competencyToProcess : deleteProcesses) {
			em.remove( competencyToProcess );
		}
		
		em.remove( foundedProject );
	}
	
	@Override
	public void addProcessToProject( final Project project, final Process process ) {
		Project foundedProject = findEntityById( Project.class, project.getId() );
		process.setProject( foundedProject );
		process.setCompany( findEntityById( Company.class, foundedProject.getCompany().getId() ) );
		persistEntity( process );
		
		List<Competency> competencies = process.getCompetencies();
		
		for (Competency competency : competencies) {
			persistEntity( new CompetencyToProcess( process, competency ) );
		}
	}
	
	@Override
	public void removeProcess( final Process process ) {
		Process foundedProject = findEntityById( Process.class, process.getId() );
		List<CompetencyToProcess> loadAllCompetencies = loadAll( CompetencyToProcess.class );
		List<CompetencyToProcess> deleteProcesses = Lists.newArrayList();
		
		for (CompetencyToProcess competencyToProcess : loadAllCompetencies) {
			
			if (competencyToProcess.getProjectUnit().equals( foundedProject )) {
				deleteProcesses.add( competencyToProcess );
			}
		}
		
		for (CompetencyToProcess competencyToProcess : deleteProcesses) {
			em.remove( competencyToProcess );
		}
		
		em.remove( foundedProject );
	}
	
	@Override
	public void addTaskToProcess( final Process process, final Task task ) {
		Process foundedProject = findEntityById( Process.class, process.getId() );
		Project foundProject = findEntityById( Project.class, process.getProject().getId() );
		task.setProcess( foundedProject );
		task.setProject( foundProject );
		task.setCompany( findEntityById( Company.class, foundedProject.getCompany().getId() ) );
		persistEntity( task );
		
		List<Competency> competencies = task.getCompetencies();
		
		for (Competency competency : competencies) {
			persistEntity( new CompetencyToTask( task, competency ) );
		}
		
	}
	
	@Override
	public void removeTask( final Task task ) {
		Task foundedProject = findEntityById( Task.class, task.getId() );
		
		List<CompetencyToTask> loadAllCompetencies = loadAll( CompetencyToTask.class );
		List<CompetencyToTask> deleteProcesses = Lists.newArrayList();
		
		for (CompetencyToTask competencyToProcess : loadAllCompetencies) {
			
			if (competencyToProcess.getProjectUnit().equals( foundedProject )) {
				deleteProcesses.add( competencyToProcess );
			}
		}
		
		for (CompetencyToTask competencyToProcess : deleteProcesses) {
			em.remove( competencyToProcess );
		}
		
		em.remove( foundedProject );
	}
	
	@Override
	public void addTaskToProject( final Project process, final Task task ) {
		Project foundedProject = findEntityById( Project.class, process.getId() );
		task.setProject( foundedProject );
		task.setCompany( findEntityById( Company.class, foundedProject.getCompany().getId() ) );
		persistEntity( task );
		
		List<Competency> competencies = task.getCompetencies();
		
		for (Competency competency : competencies) {
			persistEntity( new CompetencyToTask( task, competency ) );
		}
	}
	
}
