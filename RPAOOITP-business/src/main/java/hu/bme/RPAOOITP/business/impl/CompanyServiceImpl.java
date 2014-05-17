
package hu.bme.RPAOOITP.business.impl;

import hu.bme.RPAOOITP.business.CompanyService;
import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.domain.query.CompanyQueries;

import java.util.List;

import com.google.inject.Inject;

public class CompanyServiceImpl implements CompanyService {
	
	@Inject
	private CompanyQueries companyQueries;
	
	@Override
	public void createCompany( final User user, final Company company ) {
		companyQueries.createCompany( user, company );
	}
	
	@Override
	public void modifyCompanyName( final Company company ) {
		companyQueries.modifyCompanyName( company );
	}
	
	@Override
	public void addUserToCompany( final User user, final Company company ) {
		companyQueries.addUserToCompany( user, company );
	}
	
	@Override
	public void removeUserFromCompany( final User user, final Company company ) {
		companyQueries.removeUserFromCompany( user, company );
	}
	
	@Override
	public void addManagerToCompany( final User user, final Company company ) {
		companyQueries.addManagerToCompany( user, company );
	}
	
	@Override
	public void removeManagerFromCompany( final User user, final Company company ) {
		companyQueries.removeManagerFromCompany( user, company );
	}
	
	@Override
	public Company getCompanyByUser( final User user ) {
		return companyQueries.getCompanyByUser( user );
	}
	
	@Override
	public List<User> getEmployeesOfCompany( final Company company ) {
		return companyQueries.getEmployeesOfCompany( company );
	}
	
	@Override
	public List<User> getManagersOfCompany( final Company company ) {
		return companyQueries.getManagersOfCompany( company );
	}
}
