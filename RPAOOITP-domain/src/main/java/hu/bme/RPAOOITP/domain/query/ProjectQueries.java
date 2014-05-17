
package hu.bme.RPAOOITP.domain.query;

import hu.bme.RPAOOITP.domain.model.AbstractProjectUnit;
import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.Process;
import hu.bme.RPAOOITP.domain.model.Project;
import hu.bme.RPAOOITP.domain.model.Task;
import hu.bme.RPAOOITP.domain.query.impl.HibernateProjectQueries;

import java.util.List;

import com.google.inject.ImplementedBy;

@ImplementedBy( HibernateProjectQueries.class )
public interface ProjectQueries {
	
	List<AbstractProjectUnit> findAllProjectUnitsByCompany( final Company company );
	
	void addProjectToCompany( final Project project, final Company company );
	
	void removeProject( final Project project );
	
	void addProcessToProject( final Project project, final Process process );
	
	void removeProcess( final Process process );
	
	void addTaskToProcess( final Process process, final Task task );
	
	void removeTask( final Task task );
	
	void addTaskToProject( final Project process, final Task task );
	
}
