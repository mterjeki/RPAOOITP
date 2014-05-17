
package hu.bme.RPAOOITP.domain.query.impl;

import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.EmployeeToCompany;
import hu.bme.RPAOOITP.domain.model.ManagerToCompany;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.domain.query.CompanyQueries;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;

public class HibernateCompanyQueries extends AbstractHibernateBaseRPAOOITPQueries implements CompanyQueries {
	
	@Override
	public void createCompany( final User user, final Company company ) {
		User foundedUser = findEntityById( User.class, user.getId() );
		persistEntity( company );
		foundedUser.setCompany( company );
	}
	
	@Override
	public void modifyCompanyName( final Company company ) {
		Company foundedCompany = findEntityById( Company.class, company.getId() );
		foundedCompany.setCompanyName( company.getCompanyName() );
	}
	
	@Override
	public void addUserToCompany( final User user, final Company company ) {
		Company foundedCompany = findEntityById( Company.class, company.getId() );
		User foundedUser = findEntityById( User.class, user.getId() );
		
		persistEntity( new EmployeeToCompany( foundedUser, foundedCompany ) );
		foundedUser.setCompany( foundedCompany );
		mergeEntity( foundedCompany );
	}
	
	@Override
	public void removeUserFromCompany( final User user, final Company company ) {
		Company foundedCompany = findEntityById( Company.class, company.getId() );
		User foundedUser = findEntityById( User.class, user.getId() );
		
		EmployeeToCompany toRemoveCompany = null;
		
		List<EmployeeToCompany> loadAll = loadAll( EmployeeToCompany.class );
		
		for (EmployeeToCompany employeeToCompany : loadAll) {
			if (employeeToCompany.getUser().equals( foundedUser ) && employeeToCompany.getCompany().equals( foundedCompany )) {
				toRemoveCompany = employeeToCompany;
			}
		}
		
		em.remove( toRemoveCompany );
		foundedUser.setCompany( null );
	}
	
	@Override
	public void addManagerToCompany( final User user, final Company company ) {
		Company foundedCompany = findEntityById( Company.class, company.getId() );
		User foundedUser = findEntityById( User.class, user.getId() );
		
		persistEntity( new ManagerToCompany( foundedUser, foundedCompany ) );
	}
	
	@Override
	public void removeManagerFromCompany( final User user, final Company company ) {
		Company foundedCompany = findEntityById( Company.class, company.getId() );
		User foundedUser = findEntityById( User.class, user.getId() );
		
		ManagerToCompany toRemoveCompany = null;
		
		List<ManagerToCompany> loadAll = loadAll( ManagerToCompany.class );
		for (ManagerToCompany employeeToCompany : loadAll) {
			if (employeeToCompany.getUser().equals( foundedUser ) && employeeToCompany.getCompany().equals( foundedCompany )) {
				toRemoveCompany = employeeToCompany;
			}
		}
		em.remove( toRemoveCompany );
	}
	
	@Override
	public Company getCompanyByUser( final User user ) {
		User foundedUser = findEntityById( User.class, user.getId() );
		UUID id = foundedUser.getCompany().getId();
		Company company = findEntityById( Company.class, id );
		return company;
	}
	
	@Override
	public List<User> getEmployeesOfCompany( final Company company ) {
		List<EmployeeToCompany> loadAll = loadAll( EmployeeToCompany.class );
		List<User> users = Lists.newArrayList();
		
		for (EmployeeToCompany employeeToCompany : loadAll) {
			if (employeeToCompany.getCompany().getId().equals( company.getId() )) {
				users.add( employeeToCompany.getUser() );
			}
		}
		
		return users;
	}
	
	@Override
	public List<User> getManagersOfCompany( final Company company ) {
		List<ManagerToCompany> loadAll = loadAll( ManagerToCompany.class );
		List<User> users = Lists.newArrayList();
		
		for (ManagerToCompany employeeToCompany : loadAll) {
			if (employeeToCompany.getCompany().getId().equals( company.getId() )) {
				users.add( employeeToCompany.getUser() );
			}
		}
		
		return users;
	}
}
