
package hu.bme.RPAOOITP.domain.query;

import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.domain.query.impl.HibernateCompanyQueries;

import java.util.List;

import com.google.inject.ImplementedBy;

@ImplementedBy( HibernateCompanyQueries.class )
public interface CompanyQueries {
	
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
