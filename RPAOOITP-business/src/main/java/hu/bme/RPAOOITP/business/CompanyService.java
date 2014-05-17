
package hu.bme.RPAOOITP.business;

import hu.bme.RPAOOITP.business.impl.CompanyServiceImpl;
import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.User;

import java.util.List;

import com.google.inject.ImplementedBy;

@ImplementedBy( CompanyServiceImpl.class )
public interface CompanyService {
	
	void createCompany( final User user, final Company company );
	
	void modifyCompanyName( final Company company );
	
	void addUserToCompany( final User user, final Company company );
	
	void removeUserFromCompany( final User user, final Company company );
	
	void addManagerToCompany( final User user, final Company company );
	
	void removeManagerFromCompany( final User user, final Company company );
	
	Company getCompanyByUser( final User user );
	
	List<User> getEmployeesOfCompany( final Company company );
	
	List<User> getManagersOfCompany( final Company company );
	
}
