
package hu.bme.RPAOOITP.domain.query;

import hu.bme.RPAOOITP.domain.model.AbstractProjectUnit;
import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.Process;
import hu.bme.RPAOOITP.domain.model.Project;
import hu.bme.RPAOOITP.domain.model.Task;
import hu.bme.RPAOOITP.domain.query.impl.HibernateUserQueries;

import java.util.List;

import com.google.inject.ImplementedBy;

@ImplementedBy( HibernateUserQueries.class )
public interface ProjectQueries {
	
	List<Project> findProjectsByCompany( final Company company );
	
	List<Process> findProcessesByProject( final Project project );
	
	List<Task> findTasksByProcess( final Process process );
	
	List<AbstractProjectUnit> findAllProjectUnitsByCompany( final Company company );
	
}
