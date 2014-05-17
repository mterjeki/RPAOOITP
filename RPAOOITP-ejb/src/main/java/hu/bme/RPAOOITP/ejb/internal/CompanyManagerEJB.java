
package hu.bme.RPAOOITP.ejb.internal;

import hu.bme.RPAOOITP.business.CompanyService;
import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.ejb.CompanyManager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local( CompanyManager.class )
public class CompanyManagerEJB extends AbstractManagerEJB implements CompanyManager {
	
	@Override
	public void createCompany( final User user, final Company company ) {
		buildInjector().getInstance( CompanyService.class ).createCompany( user, company );
	}
	
	@Override
	public void modifyCompanyName( final Company company ) {
		buildInjector().getInstance( CompanyService.class ).modifyCompanyName( company );
	}
	
	@Override
	public void addUserToCompany( final User user, final Company company ) {
		buildInjector().getInstance( CompanyService.class ).addUserToCompany( user, company );
	}
	
	@Override
	public void removeUserFromCompany( final User user, final Company company ) {
		buildInjector().getInstance( CompanyService.class ).removeUserFromCompany( user, company );
	}
	
	@Override
	public void addManagerToCompany( final User user, final Company company ) {
		buildInjector().getInstance( CompanyService.class ).addManagerToCompany( user, company );
	}
	
	@Override
	public void removeManagerFromCompany( final User user, final Company company ) {
		buildInjector().getInstance( CompanyService.class ).removeManagerFromCompany( user, company );
	}
	
	@Override
	public Company getCompanyByUser( final User user ) {
		return buildInjector().getInstance( CompanyService.class ).getCompanyByUser( user );
	}
	
	@Override
	public List<User> getEmployeesOfCompany( final Company company ) {
		return buildInjector().getInstance( CompanyService.class ).getEmployeesOfCompany( company );
	}
	
	@Override
	public List<User> getManagersOfCompany( final Company company ) {
		return buildInjector().getInstance( CompanyService.class ).getManagersOfCompany( company );
	}
	
}
