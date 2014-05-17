
package hu.bme.RPAOOITP.ejb;

import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.User;

import java.util.List;

public interface CompanyManager {
	
	void createCompany( final User user, final Company company );
	
	Company getCompanyByUser( final User user );
	
	void modifyCompanyName( final Company company );
	
	void addUserToCompany( final User user, final Company company );
	
	void removeUserFromCompany( final User user, final Company company );
	
	void addManagerToCompany( final User user, final Company company );
	
	void removeManagerFromCompany( final User user, final Company company );
	
	List<User> getEmployeesOfCompany( final Company company );
	
	List<User> getManagersOfCompany( final Company company );
	
}
